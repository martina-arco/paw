package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private ReceiptDao receiptDao;

    @Autowired
    private StadiumDao stadiumDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private PlayerStatsDao playerStatsDao;

    @Override
    public List<String> getUpcomingMatches(Team team, Date currentDate) {
//        List<String> teamNames = new ArrayList<>();
//        List<Match> matches = matchDao.findByTeamIdFromDate(team.getId(), currentDate);
//
//        for (Match match:matches) {
//
//            Team home = match.getHome();
//            Team away = match.getAway();
//
//            if(home.equals(team))
//                teamNames.add(home.getName());
//            else
//                teamNames.add(away.getName());
//        }
//
//        return teamNames;
        return new ArrayList<>();
    }

    public void MatchEnd(long matchId) {
        Match match = matchDao.findById(matchId);
        Team team = match.getHome();

        saveEvents(match);
        addMatchEarnings(team);

        boolean isNextMonth = advanceDate(match);
        if(isNextMonth) {
            subtractPlayerSalaries(team);
        }

        teamDao.save(team);
        matchDao.save(match);
    }

    private void saveEvents(Match match) {
        for (Event event : match.getEvents()) {
            eventDao.create(match, event.getP1(), event.getP2(), event.getType(), event.getMinute());
        }

        for (PlayerStats playerStat : match.getStats()) {
            playerStatsDao.save(playerStat);
        }
    }

    private void subtractPlayerSalaries(Team team) {
        int amount = team.getSalaries();
        receiptDao.create(team, amount, Receipt.Type.PLAYERSSALARIES);
        team.addMoney(-amount);
    }

    private void addMatchEarnings(Team team) {
        int amount = stadiumDao.findById(team.getStadiumId()).calculateMatchEarnings(team.getFanCount());
        receiptDao.create(team, amount, Receipt.Type.MATCHINCOME);
        team.addMoney(amount);
    }

    private boolean advanceDate(Match match) {
        match.finish();
        return false;
    }
}
