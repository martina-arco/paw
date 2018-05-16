package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.StadiumDao;
import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.StadiumService;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StadiumServiceImpl implements StadiumService {

    @Autowired
    StadiumDao stadiumDao;

    @Override
    public Stadium findByTeam(Team team) {
//        return stadiumDao.findByTeamId(team.getId());
        return new Stadium(1,"Monumental", team,10,10,20,20,30,30);
    }
}
