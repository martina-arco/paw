package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.*;
import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
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
    private AiService aiService;

    @Autowired
    private StadiumService stadiumService;

    @Autowired
    private EventDao eventDao;

    @Autowired
    private EconomyService economyService;

    @Override
    public void getScores(Match match, Map<String, Integer> homeScores, Map<String, Integer> awayScores) {

        for (Event event : match.getEvents()) {
            if(event.getType() == Event.Type.HOMESCORE || event.getType() == Event.Type.AWAYSCORE) {
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
            fetchEvents(match);

            Team home = match.getHome();
            teamService.setPlayers(home);
            if(home.getFormation() == null) {
                home.setFormation(aiService.getFormation(home.getPlayers()));
            }
            teamService.setFormation(home);


            Team away = match.getAway();
            teamService.setPlayers(away);
            if(away.getFormation() == null) {
                away.setFormation(aiService.getFormation(away.getPlayers()));
            }
            teamService.setFormation(away);
        }
    }

    @Override
    public List<Match> getUpcomingMatches(Team team, Date currentDate) {
        List<Match> matches = matchDao.findByTeamIdFromDate(team.getId(), currentDate);
        setTeams(matches);

        Collections.sort(matches, new Comparator<Match>() {
            @Override
            public int compare(Match o1, Match o2) {
                if (o1.getDay() == null || o2.getDay() == null)
                    return 0;
                return o1.getDay().compareTo(o2.getDay());
            }
        });

        return matches;
    }

    @Override
    public void setTeams(List<Match> matches) {
        //Not necessary with hibernate, default fetch is EAGER
    }

    @Override
    public void payTickets(Match match) {
        Team home = match.getHome();
        Stadium stadium = home.getStadium();
        float factor = home.getFanTrust()/100;
        int income = (int) (stadium.calculateMatchEarnings()*factor);
        economyService.submitReceipt(home, Receipt.Type.MATCHINCOME, income);
    }

    @Override
    public void fetchEvents(Match match) {
        match.getEvents().size();
    }

    @Override
    @Deprecated
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
                eventDao.create(match, event.getP1(), event.getP2(), event.getType(), event.getMinute());
            }
            matchDao.save(match);
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
