package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
    private UserDao userDao;

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private LeagueService leagueService;

    @Autowired
    private EventDao eventDao;

    @Override
    public void getScores(Match match, Map<String, Integer> homeScores, Map<String, Integer> awayScores) {

        for (Event event : match.getEvents()) {
            if(event.getType() == Event.Type.SCORE) {
                Player player = event.getP1();

                if(player.getTeam().equals(match.getHome()))
                    homeScores.put(player.getName(), event.getMinute());
                else
                    awayScores.put(player.getName(), event.getMinute());
            }
        }

    }

    @Override
    public List<String> getUpcomingMatches(Team team, Date currentDate) {
        List<String> teamNames = new ArrayList<>();
        List<Match> matches = matchDao.findByTeamIdFromDate(team.getId(), currentDate);

        for (Match match:matches) {

            Team home = match.getHome();
            Team away = match.getAway();

            if(home.equals(team))
                teamNames.add(home.getName());
            else
                teamNames.add(away.getName());
        }

        return teamNames;

    }

    @Override
    public void UserMatchEnd(Match match, User user) {
        Team team = match.getHome();
        if(match.getHome().equals(user.getTeam())) {
            addMatchEarnings(team);
        }
        else {
            team = match.getAway();
        }
        advanceDate(match, user ,team);

        teamDao.save(team);
        matchDao.save(match);
        userDao.save(user);
    }

    private void subtractPlayerSalaries(Team team) {
        int amount = team.getSalaries();
        receiptDao.create(team, amount, Receipt.Type.PLAYERSSALARIES);
        team.addMoney(-amount);
    }

    private void addMatchEarnings(Team team) {
        int amount = stadiumDao.findById(team.getStadiumId()).calculateMatchEarnings();
        receiptDao.create(team, amount, Receipt.Type.MATCHINCOME);
        team.addMoney(amount);
    }

    private void advanceDate(Match match ,User user, Team team) {
        Calendar cal = Calendar.getInstance();
        Date currentDate = user.getCurrentDay();
        cal.setTime(currentDate);
        cal.add(Calendar.DATE, 7);
        Date newDate = cal.getTime();
        user.setCurrentDay(newDate);
        if(newDate.getMonth() > currentDate.getMonth()) {
            subtractPlayerSalaries(team);
        }
        if(matchDao.findByTeamIdFromDate(team.getId(), newDate).isEmpty()) {
            League league = leagueDao.findById(team.getLeagueId());
            int higherPoints = 0;
            Team higherTeam = null;
            for (Map.Entry<Team, Integer> entry : leagueService.getTeamPoints(league, currentDate).entrySet())
            {
                if(entry.getValue() > higherPoints) {
                    higherPoints = entry.getValue();
                    higherTeam = entry.getKey();
                }
            }
            int amount = league.getPrize();
            if(higherTeam.equals(team)) {
                receiptDao.create(team, amount, Receipt.Type.TOURNAMENTPRICE);
                team.addMoney(amount);
            }
        }
    }

    @Override
    public void FinishMatches(List<Match> matches) {
        for (Match match: matches) {
            match.finish();
            for (Event event: match.getEvents()) {
                eventDao.create(match, event.getP1(), event.getP2(), event.getType(), event.getMinute());
            }
            matchDao.save(match);
        }
    }

    @Override
    public Match getUserMatch(List<Match> matches, Team userTeam) {
        for (Match match :matches) {
            if(match.getHome().equals(userTeam) || match.getAway().equals(userTeam)) {
                return match;
            }
        }

        return null;
    }

    @Override
    public Match create(League league, Team home, Team away, Date day) {
        return matchDao.create(league, home, away, day);
    }

    @Override
    public Match create(long league, long home, long away, Date day) {
        return matchDao.create(league, home, away, day);
    }

    @Override
    public boolean save(Match match) {
        return matchDao.save(match);
    }

    @Override
    public Match findById(long id) {
        return matchDao.findById(id);
    }

    @Override
    public List<Match> findByTeamId(long id) {
        return matchDao.findByTeamId(id);
    }

    @Override
    public List<Match> findByTeamIdFromDate(long id, Date date) {
        return matchDao.findByTeamIdFromDate(id, date);
    }

    @Override
    public List<Match> findByLeagueId(long id) {
        return matchDao.findByLeagueId(id);
    }

    @Override
    public List<Match> findByLeagueIdAndDate(long id, Date date) {
        return matchDao.findByLeagueIdAndDate(id, date);
    }

    @Override
    public List<Match> findByLeagueIdAndBeforeDate(long id, Date date) {
        return matchDao.findByLeagueIdAndBeforeDate(id, date);
    }
}
