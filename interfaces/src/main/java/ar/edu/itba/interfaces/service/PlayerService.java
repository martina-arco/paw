package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;

public interface PlayerService {

    Player create(String name, int age, int value, int potential, int skillLevel, Contract contract,
                  int goalkeeping, int finish, int defending, int passing, int fitness);

    Player findById(long id);

}
