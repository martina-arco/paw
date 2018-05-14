package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.PlayerDao;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    @Override
    @Transactional
    public Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping,
                         int finish, int defending, int passing, int fitness, int salary, Date contractExpiration) {
        return playerDao.create(name, team, age, value, potential, skillLevel, goalkeeping, finish, defending, passing,
                fitness, salary, contractExpiration);
    }

    @Override
    public Player findById(long id) {
        return playerDao.findById(id);
        //return new Player(1, "asd", 0,0,0,0, null, 0,0,0,0,0);
    }
}
