package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.PlayerDao;
import ar.edu.itba.interfaces.service.PlayerService;
import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PlayerServiceImpl implements PlayerService {

    @Autowired
    private PlayerDao playerDao;

    @Override
    @Transactional
    public Player create(String name, int age, int value, int potential, int skillLevel, Contract contract, int goalkeeping, int finish, int defending, int passing, int fitness) {
        return playerDao.create(name, age, value, potential, skillLevel, contract, goalkeeping, finish, defending, passing, fitness);
    }

    @Override
    public Player findById(long id) {
        return playerDao.findById(id);
    }
}
