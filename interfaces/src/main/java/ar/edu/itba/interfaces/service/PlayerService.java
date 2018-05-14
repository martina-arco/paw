package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Contract;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;

import java.util.Date;

public interface PlayerService {

    Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping,
                  int finish, int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth);

    Player findById(long id);

}
