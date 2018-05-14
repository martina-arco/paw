package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.TeamDao;
import ar.edu.itba.interfaces.service.TeamService;
import ar.edu.itba.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    @Autowired
    private TeamDao teamDao;

    @Override
    @Transactional
    public Team create(String name, League league, Stadium stadium, Formation formation, List<Player> players, List<Player> youthAcademy, Integer fanTrust, Integer boardTrust, Integer money) {
        return teamDao.create(name, league, stadium, formation, players, youthAcademy, fanTrust, boardTrust, money);
    }

    @Override
    public Team findById(long id) {
        return teamDao.findById(id);
    }
}
