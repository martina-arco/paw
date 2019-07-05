package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.EventDao;
import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.MatchStateDao;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.model.*;
import ar.edu.itba.model.utils.*;
import ar.edu.itba.model.simulation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static ar.edu.itba.model.simulation.Grid.otherTeam;
import static ar.edu.itba.model.simulation.MyTeam.AWAY;
import static ar.edu.itba.model.simulation.MyTeam.HOME;

@Service
@Transactional
public class SimulationServiceImpl implements SimulationService{

    @Autowired
    private MatchStateDao matchStateDao;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private MatchService matchService;

    public SimulationServiceImpl(){

    }

    @Override
    public void simulateFixture(Long userId, List<Match> matches) {
        for(Match match : matches){
            new MatchSimulator(match, null).simulate();
        }
    }

    @Override
    public List<Match> getMatches(Long leagueId, User user) {
        List<Match> matches = matchDao.findByLeagueIdAndDate(leagueId, user.getCurrentDay());
        List<Match> ret = new ArrayList<>();
        for(Match match : matches){
            matchService.fetchEvents(match);
            ret.add(match);
        }

        return ret;
    }

    @Override
    public boolean started(Match match) {
        return matchStateDao.findByMatch(match) != null;
    }

    @Override
    public void resimulate(Long userId, int minute, Match match, Team team, List<Player> in, List<Player> out){
        for (int i = 0; i < in.size(); i++) {
            team.getFormation().replacePlayer(in.get(i), out.get(i));
        }
        MatchDeepStatus matchDeepStatus = matchStateDao.findByMatch(match);
        new MatchSimulator(match, matchDeepStatus).simulate(minute, matchDeepStatus.positionByMinute(minute), matchDeepStatus.ownerByMinute(minute));
    }

    private class MatchSimulator{
        private final Match match;
        private final MatchStatus matchStatus;
        private final MatchDeepStatus deepStatus;

        private MatchSimulator(Match match, MatchDeepStatus matchDeepStatus) {
            super();
            this.matchStatus = new MatchStatus(0, 0,0, new ArrayList<Event>());
            this.match = match;
            if(matchDeepStatus == null)
                this.deepStatus = matchStateDao.create(match);
            else
                this.deepStatus = matchDeepStatus;
        }

        private void finish() {
            matchStatus.setMinute(90);

            for (Event event : matchStatus.getEvents()) {
                match.addEvent(event);
            }

            match.addAwayScore(matchStatus.getAwayScore());
            match.addHomeScore(matchStatus.getHomeScore());

            match.finish();

            matchDao.save(match);
            matchStateDao.save(deepStatus);
        }

        private void simulate(){
            simulate(0, null, HOME);
        }

        private void simulate(int minute, Point position, MyTeam possession) {
            Formation home = match.getHome().getFormation(), away = match.getAway().getFormation();
            Grid matchGrid = new Grid(match, home, away);
            SimulationNode currentState, lastState;

            if(position == null){
                currentState = matchGrid.kickOff(HOME);
            } else {
                currentState = matchGrid.getNode(position,possession);
            }

            while (minute < 90) {
                if (minute == 45)
                    currentState = matchGrid.kickOff(AWAY);

                matchStatus.setMinute(minute);

                lastState = currentState;
                currentState = dispute(currentState, matchStatus);

                if (currentState.equals(lastState))
                    currentState = advance(currentState, matchStatus);

                deepStatus.registerState(currentState.getNode().getPosition(), minute, currentState.getPossession());
                minute++;
            }

            finish();
        }

        public boolean equals(Object o) {
            if (o == null)
                return false;
            if (o == this)
                return true;
            if (!o.getClass().equals(MatchSimulator.class))
                return false;
            MatchSimulator aux = (MatchSimulator) o;
            return aux.match.equals(match);
        }
    }

    public SimulationNode dispute(SimulationNode sNode, MatchStatus matchStatus) {
        GridNode node = sNode.getNode();
        MyTeam possession = sNode.getPossession();
        int opponentDef = node.getAtt(otherTeam(possession), NodeAtt.DEF);
        int myPoss = node.getAtt(possession, NodeAtt.POSS);

        int sum = myPoss + opponentDef + 1;
        double nMyPoss = (double) myPoss / sum;

        boolean taken = sNode.getGrid().getRand().nextDouble() < nMyPoss;

        if (taken) {
            matchStatus.getEvents().add(eventDao.create(sNode.getGrid().getMatch(), node.getSNode(otherTeam(possession)).whoDidIt(), null,  Event.Type.TACKLE, matchStatus.getMinute()));
        }

        return node.getSNode(taken ? otherTeam(possession) : possession);
    }

    public SimulationNode shot(SimulationNode sNode, MatchStatus matchStatus) {
        Grid grid = sNode.getGrid();
        GridNode node = sNode.getNode();
        MyTeam possession = sNode.getPossession();
        Player opGK = sNode.getOpGK();
        double check = grid.getRand().nextDouble();
        int shot = node.getAtt(possession, NodeAtt.FIN) / (sNode.distanceToGoal() + 1);

        int sum = shot + opGK.getGoalKeeping() + node.getAtt(otherTeam(possession), NodeAtt.DEF);
        double norm = (double) shot / sum;
        boolean goal = check < norm;

        if (goal) {

            if (possession.equals(MyTeam.AWAY)) {
                matchStatus.setAwayScore(matchStatus.getAwayScore() + 1);
                matchStatus.getEvents().add(eventDao.create(grid.getMatch(), sNode.whoDidIt(), null, Event.Type.AWAYSCORE, matchStatus.getMinute()));
            }
            else {
                matchStatus.setHomeScore(matchStatus.getHomeScore() + 1);
                matchStatus.getEvents().add(eventDao.create(grid.getMatch(), sNode.whoDidIt(), null,Event.Type.HOMESCORE, matchStatus.getMinute()));
            }

            return grid.kickOff(otherTeam(possession));
        }

        matchStatus.getEvents().add(eventDao.create(grid.getMatch(), opGK, null,Event.Type.SAVE, matchStatus.getMinute()));
        return grid.goalKick(otherTeam(possession));
    }

    public SimulationNode advance(SimulationNode sNode, MatchStatus matchStatus) {
        double chanceToShoot = sNode.distanceToGoal() == 0 ? 1.0 : 1 / (sNode.distanceToGoal() * 2);
        boolean shooting = sNode.getGrid().getRand().nextDouble() < chanceToShoot;

        if (sNode.distanceToGoal() < 2 || shooting)
            return shot(sNode, matchStatus);

        double random = sNode.getGrid().getRand().nextDouble();
        double accum = 0;
        SimulationNode last = sNode;
        for (SimulationArc arc : sNode.getNeighbors()) {
            accum += arc.getWeight();
            if (random < accum)
                return arc.getNeighbor();
            last = arc.getNeighbor();
        }

        matchStatus.getEvents().add(eventDao.create(sNode.getGrid().getMatch(),sNode.whoDidIt(),null,Event.Type.PASS,matchStatus.getMinute()));

        return last;
    }
}
