package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.MatchStateDao;
import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.model.*;
import ar.edu.itba.model.utils.*;
import ar.edu.itba.model.utils.simulation.Grid;
import ar.edu.itba.model.utils.simulation.MatchDeepStatus;
import ar.edu.itba.model.utils.simulation.MyTeam;
import ar.edu.itba.model.utils.simulation.SimulationNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static ar.edu.itba.model.utils.simulation.MyTeam.AWAY;
import static ar.edu.itba.model.utils.simulation.MyTeam.HOME;

@Service
public class SimulationServiceImpl implements SimulationService{

    @Autowired
    MatchStateDao matchStateDao;

    public SimulationServiceImpl(){

    }

    @Override
    public void simulateFixture(Long userId, List<Match> matches) {
        for(Match match : matches){
            new MatchSimulator(match, null).simulate();
        }
    }

    @Override
    public Map<Long, MatchStatus> getStatus(Long userId) {
        return null;
    }

    @Override
    public List<Match> getMatches(Long userId) {
        return null;
    }

    public void resimulate(Long userId,int minute, Match match, Team team, Player in, Player out){
        team.getFormation().replacePlayer(in, out);
        MatchDeepStatus matchDeepStatus = matchStateDao.findByMatch(match);
        new MatchSimulator(match, matchDeepStatus).simulate(minute, matchDeepStatus.positionByMinute(minute), matchDeepStatus.ownerByMinute(minute));
    }

    private class MatchSimulator{
        private final Match match;
        private final MatchStatus matchStatus, persistedMatchStatus;
        private final MatchDeepStatus deepStatus;


        private MatchSimulator(Match match, MatchDeepStatus matchDeepStatus) {
            super();
            this.matchStatus = new MatchStatus(0, 0,0, new ArrayList<Event>());
            this.persistedMatchStatus = new MatchStatus(0,0,0,new ArrayList<>());
            this.match = match;
            if(matchDeepStatus == null)
                this.deepStatus = new MatchDeepStatus(match);
            else
                this.deepStatus = matchDeepStatus;
        }

        private void finish() {
            matchStatus.setMinute(90);
            persistedMatchStatus.setMinute(90);

            for (Event event : persistedMatchStatus.getEvents()) {
                match.addEvent(event);
            }

            persistedMatchStatus.setHomeScore(matchStatus.getHomeScore());
            persistedMatchStatus.setAwayScore(matchStatus.getAwayScore());

            match.addAwayScore(persistedMatchStatus.getAwayScore());
            match.addHomeScore(persistedMatchStatus.getHomeScore());

            match.finish();

            matchStateDao.save(deepStatus);
        }

        private void simulate(){
            simulate(0, null, HOME);
        }

        private void simulate(int minute, Point position, MyTeam possession) {
            Formation home = match.getHome().getFormation(), away = match.getAway().getFormation();
            Grid matchGrid = new Grid(home, away);
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
                currentState = currentState.dispute(matchStatus);

                if (currentState.equals(lastState))
                    currentState = currentState.advance(matchStatus);

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
}
