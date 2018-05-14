package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.LeagueDao;
import ar.edu.itba.interfaces.service.LeagueService;
import ar.edu.itba.model.League;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {

    @Autowired
    LeagueDao leagueDao;

    @Override
    public List<League> findByUser(User user) {
        return leagueDao.findAllByUserId(user.getId());
    }
}
