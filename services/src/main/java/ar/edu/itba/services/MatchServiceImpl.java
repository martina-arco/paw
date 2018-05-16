package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.AiService;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.interfaces.service.StadiumService;
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
    private TeamService teamService;

    @Autowired
    private AiService AiService;

    @Autowired
    private StadiumService stadiumService;

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
    public void setTeamsAndFormations(List<Match> matches) {
        for (Match match : matches) {
            Team home = teamDao.findById(match.getHomeId());
            if(home != null) {
                teamService.setPlayers(home);
                teamService.setFormation(home);
            }
            Team away = teamDao.findById(match.getAwayId());
            if(away != null) {
                teamService.setPlayers(away);
                teamService.setFormation(away);
            }
            match.setHome(home);
            match.setAway(away);
        }
    }

    @Override
    public List<Match> getUpcomingMatches(Team team, Date currentDate) {
        List<Match> matches = matchDao.findByTeamIdFromDate(team.getId(), currentDate);
        setTeams(matches);
        return matches;
    }

    public void setTeams(List<Match> matches) {

        Set<Team> teams = new HashSet<>();

        for (Match match:matches) {

            Team home = teamDao.findById(match.getHomeId());
            Team away = teamDao.findById(match.getAwayId());

            match.setHome(home);
            match.setAway(away);

            teams.add(home);
        }

        stadiumService.setStadium(teams);

    }

    @Override
    public void UserMatchEnd(Match match, User user) {
        Team team = match.getHome();
        if(match.getHomeId() == user.getTeamId()) {
            addMatchEarnings(team);
        }
        else {
            team = match.getAway();
        }
        teamDao.save(team);
    }

    private void addMatchEarnings(Team team) {
        int amount = stadiumDao.findByTeamId(team.getId()).calculateMatchEarnings();
        receiptDao.create(team, amount, Receipt.Type.MATCHINCOME);
        team.addMoney(amount);
    }

    @Override
    public void saveMatches(List<Match> matches, User user) {
        for (Match match: matches) {
            if(match.getHomeId() == user.getTeamId()) {
                addMatchEarnings(match.getHome());
            }
            for (Event event: match.getEvents()) {
                //eventDao.create(match, event.getP1(), event.getP2(), event.getType(), event.getMinute());
            }
            //matchDao.save(match);
        }
    }

    @Override
    public Match getUserMatch(List<Match> matches, User user) {
        for (Match match :matches) {
            if(match.getHomeId() == user.getTeamId() || match.getAwayId() == user.getTeamId()) {
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
