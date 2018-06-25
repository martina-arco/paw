package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.MatchStateDao;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.SimulationService;
import ar.edu.itba.model.*;
import ar.edu.itba.model.DTOs.MatchDTO;
import ar.edu.itba.model.utils.*;
import ar.edu.itba.model.utils.simulation.Grid;
import ar.edu.itba.model.utils.simulation.MatchDeepStatus;
import ar.edu.itba.model.utils.simulation.MyTeam;
import ar.edu.itba.model.utils.simulation.SimulationNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static ar.edu.itba.model.utils.simulation.MyTeam.AWAY;
import static ar.edu.itba.model.utils.simulation.MyTeam.HOME;

@Service
@Transactional
public class SimulationServiceImpl implements SimulationService{

    @Autowired
    private MatchStateDao matchStateDao;

    @Autowired
    private MatchDao matchDao;

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
    public List<MatchDTO> getMatches(Long leagueId, User user) {
        List<Match> matches = matchDao.findByLeagueIdAndDate(leagueId, user.getCurrentDay());
        List<MatchDTO> ret = new ArrayList<>();
        for(Match match : matches){
            matchService.fetchEvents(match);
            ret.add(new MatchDTO(match));
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
