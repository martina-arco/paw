package ar.edu.itba.services;

import ar.edu.itba.interfaces.dao.ContractDao;
import ar.edu.itba.interfaces.service.ContractService;
import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.utils.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContractServiceImpl implements ContractService {

    @Autowired
    private ContractDao contractDao;

    @Override
    @Transactional
    public Contract create(Team team, Player p, int salary, Date length) {
        return contractDao.create(team, p, salary, length);
    }

    @Override
    public Contract findById(long id) {
        return contractDao.findById(id);
    }

    @Override
    public List<Contract> findByTeam(Team team) {
        return contractDao.findByTeam(team);
    }
}
