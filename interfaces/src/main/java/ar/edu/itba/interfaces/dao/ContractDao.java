package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.utils.Date;

import java.util.List;

/**
 * Created by martina on 02/05/2018.
 */
public interface ContractDao {

    Contract create(Team team, Player p, int salary, Date length);
    List<Contract> findByTeam(Team team);
}
