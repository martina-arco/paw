package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.StadiumDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.StadiumService;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    private StadiumDao stadiumDao;

    @Override
    public Stadium findByTeam(Team team) {
        return stadiumDao.findByTeamId(team.getId());
    }

    @Override
    public void setStadium(Set<Team> teams) {
        for (Team team:teams) {
            Stadium stadium = stadiumDao.findByTeamId(team.getId());

            team.setStadium(stadium);
        }
    }

    @Override
    public void setStadium(List<Match> matches) {
        for (Match match:matches) {
            Team home = match.getHome();
            Stadium stadium = stadiumDao.findByTeamId(home.getId());
            home.setStadium(stadium);
        }
    }
}
