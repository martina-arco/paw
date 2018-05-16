package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private MatchDao matchDao;

    @Autowired
    private TeamDao teamDao;


    @Override
    public List<League> findByUser(User user) {
        List<League> leagues = leagueDao.findAllByUserId(user.getId());

        return leagues;
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

        if(matches != null) {

            for (Match match : matches) {
                Team home = match.getHome();
                Team away = match.getAway();

                int homeInitialPoints = 0;
                int awayInitialPoints = 0;

                if(map.containsKey(home))
                    homeInitialPoints = map.get(home);

                if(map.containsKey(away))
                    awayInitialPoints = map.get(away);

                homeInitialPoints += match.getHomePoints();
                awayInitialPoints += match.getAwayPoints();

                map.put(home, homeInitialPoints);
                map.put(away, awayInitialPoints);
            }

        } else {
            for (Team team : teamDao.findAllByLeagueId(league.getId())) {
                map.put(team, 0);
            }
        }

        return map;
    }

    @Override
    public List<Match> findMatchesForDate(League league, Date date) {
        if(league != null)
            return matchDao.findByLeagueIdAndDate(league.getId(), date);
        else
            return null;
    }
}
