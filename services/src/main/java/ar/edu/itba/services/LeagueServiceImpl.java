package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import ar.edu.itba.model.utils.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        return leagueDao.findAllByUserId(user.getId());
    }

    @Override
    public Map<Team, Integer> getTeamPoints(League league, java.util.Date currentDate) {

        Map<Team, Integer> map = new HashMap<>();
//        List<Match> matches = matchDao.findByLeagueIdAndBeforeDate(league.getId(), currentDate);
//
//        for (Match match : matches) {
//            Team home = match.getHome();
//            Team away = match.getAway();
//
//            int homeInitialPoints = map.get(home.getName());
//            int awayInitialPoints = map.get(away.getName());
//
//            map.put(home.getName(), homeInitialPoints += match.getHomePoints());
//            map.put(away.getName(), awayInitialPoints += match.getAwayPoints());
//        }

        return map;
    }
}
