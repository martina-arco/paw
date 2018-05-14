package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;

import java.util.Date;

/**
 * Created by martina on 02/05/2018.
 */
public interface PlayerDao {

    Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping, int finish,
                         int defending, int passing, int fitness, int salary, Date contractExpiration);

    Player findById(long id);

}
