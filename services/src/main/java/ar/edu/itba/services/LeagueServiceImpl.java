package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.*;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class LeagueServiceImpl implements LeagueService {


    @Autowired
    private FixtureService fixtureService;

    @Autowired
    private MatchService matchService;

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private TeamDao teamDao;

    @Autowired
    private EconomyService economyService;

    @Autowired
    private UserService userService;


    @Override
    public List<League> findByUser(User user) {
        List<League> leagues = leagueDao.findAllByUserId(user.getId());

        return leagues;
    }

    @Override
    public boolean isLastMatch(League league, User user) {
        fillFixture(user, league);
        Map<Date, List<Match>> fixture = league.getFixture();
        for (Map.Entry<Date, List<Match>> entry: fixture.entrySet()) {
            if(entry.getKey().after(user.getCurrentDay()))
                return false;
        }
        return true;
    }

    @Override
    public void fillFixture(User user, League league){
        Map<Date,List<Match>> fixture = new HashMap<>();

        List<Match> toPlay = matchDao.findByLeagueIdAndFromDate(league.getId(),user.getCurrentDay());

        for(Match match : toPlay){
            if(!fixture.containsKey(match.getDay())){
                fixture.put(match.getDay(), new ArrayList<>());
            }
            fixture.get(match.getDay()).add(match);
        }

        league.setFixture(fixture);
    }

    @Override
    public Map<Team, Integer> getTeamPoints(League league, Date currentDate) {

        Map<Team, Integer> map = new HashMap<>();

        List<Match> matches = matchDao.findByLeagueIdAndBeforeDate(league.getId(), currentDate);

        matchService.setTeams(matches);

        for (Team team : teamDao.findAllByLeagueId(league.getId())) {
            map.put(team, 0);
        }

        if(!matches.isEmpty()) {

            for (Match match : matches) {

                Team home = match.getHome();
                Team away = match.getAway();

                Integer homePoints = map.get(home);
                Integer awayPoints = map.get(away);

                if(homePoints == null)
                    homePoints = 0;

                if(awayPoints == null)
                    awayPoints = 0;

                homePoints += match.getHomePoints();
                awayPoints += match.getAwayPoints();

                map.put(home, homePoints);
                map.put(away, awayPoints);
            }

        }

        return map;
    }

    @Override
    public List<Map.Entry<String, Integer>> getTeamPointsName(League league, Date currentDate) {

        Map<String, Integer> map = new HashMap<>();

        List<Match> matches = matchDao.findByLeagueIdAndBeforeDate(league.getId(), currentDate);

        matchService.setTeams(matches);



        for (Team team : teamDao.findAllByLeagueId(league.getId())) {
            map.put(team.getName(), 0);
        }

        if(!matches.isEmpty()) {

            for (Match match : matches) {

                Team home = match.getHome();
                Team away = match.getAway();

                Integer homePoints = map.get(home.getName());
                Integer awayPoints = map.get(away.getName());

                if(homePoints == null)
                    homePoints = 0;

                if(awayPoints == null)
                    awayPoints = 0;

                homePoints += match.getHomePoints();
                awayPoints += match.getAwayPoints();

                map.put(home.getName(), homePoints);
                map.put(away.getName(), awayPoints);
            }

        }

        List<Map.Entry<String,Integer>> ret = new ArrayList<>(map.entrySet());

        ret.sort((o1, o2) -> o2.getValue() - o1.getValue());

        return ret;
    }

    @Override
    public void generateFixture(User user, League league) {
        Map<Date, List<Match>> fixture = fixtureService.generateFixture(league, user.getCurrentDay());
        league.setFixture(fixture);
    }

    @Override
    public int matchesPlayed(User user, Team team) {
        return matchService.findByTeamIdBeforeDate(team.getId(), user.getCurrentDay()).size();
    }

    @Override
    public int matchesToPlay(User user, Team team) {
        return matchService.findByTeamIdFromDate(team.getId(), user.getCurrentDay()).size();
    }

    @Override
    public List<Match> findMatchesForDate(League league, Date date) {
        if(league != null) {
            List<Match> matches =  matchDao.findByLeagueIdAndDate(league.getId(), date);
            matchService.setTeamsAndFormations(matches);
            return matches;
        }
        return null;
    }

    @Override
    public void finish(League league, User user){
        Map<Team, Integer> standings = getTeamPoints(league, user.getCurrentDay());
        Team winner = null;
        int winnerPoints = -1;

        for(Map.Entry<Team, Integer> standing : standings.entrySet()){
            if(standing.getValue() > winnerPoints){
                winner = standing.getKey();
                winnerPoints = standing.getValue();
            }
        }
        economyService.submitReceipt(winner, Receipt.Type.TOURNAMENTPRIZE, league.getPrize());
    }

    @Override
    public int getSeason(Team team) {
        List<Match> matches = matchService.findByTeamId(team.getId());

        Set<Integer> years = new HashSet<>();

        for (Match match : matches) {
            Date date = match.getDay();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            years.add(calendar.get(Calendar.YEAR));
        }

        return years.size();
    }

}
