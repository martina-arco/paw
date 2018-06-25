package ar.edu.itba.interfaces.service;

import ar.edu.itba.model.Player;
import ar.edu.itba.model.Team;

import java.util.Date;
import java.util.List;

public interface PlayerService {

    Player findById(List<Player> players, long id);

    Player create(String name, Team team, int age, int value, int potential, int skillLevel, int goalkeeping,
                  int finish, int defending, int passing, int fitness, int salary, Date contractExpiration, boolean youth);

    Player findById(long id);

    void retire(Player player);

    List<Player> getPlayers(Team team);

}
