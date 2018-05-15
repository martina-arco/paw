package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.PlayerStats;

import java.util.List;


public interface PlayerStatsDao {

    boolean save(PlayerStats playerStats);
    PlayerStats create(Player p, Match m);
    PlayerStats create(long p, long m);
    List<PlayerStats> findByMatchId(long id);

}
