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

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    private LeagueDao leagueDao;

    @Autowired
    private MatchDao matchDao;

    @Override
    public List<League> findByUser(User user) {
        return leagueDao.findAllByUserId(user.getId());
    }

    @Override
    public Map<Team, Integer> getTeamPoints(League league, Date currentDate) {

        Map<Team, Integer> map = new HashMap<>();

        List<Match> matches = matchDao.findByLeagueIdAndBeforeDate(league.getId(), currentDate);

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

        return map;
    }
}
