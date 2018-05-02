package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;

/**
 * Created by martina on 02/05/2018.
 */
public interface PlayerDao {

    Player create(String name, int age, int value, int potential, int skillLevel, Contract contract,
                  int goalkeeping, int finish, int defending, int passing, int fitness);
}
