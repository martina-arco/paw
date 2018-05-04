package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.utils.Date;

import java.util.List;

public interface ContractService {

    Contract create(Team team, Player p, int salary, Date length);

    Contract findById(long id);

    List<Contract> findByTeam(Team team);

}
