package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.MatchDao;
import ar.edu.itba.interfaces.service.MatchService;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchDao matchDao;

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
}
