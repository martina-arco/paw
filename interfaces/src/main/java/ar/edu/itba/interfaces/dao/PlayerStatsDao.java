package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.EventType;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;
import ar.edu.itba.model.PlayerStats;

import java.util.List;

/**
 * Created by martina on 01/05/2018.
 */
public interface PlayerStatsDao {

    void save(Match m, Player p, EventType type, int amount);
    PlayerStats create(Player p, Match m);
    List<PlayerStats> findByMatch(Match m);
    List<PlayerStats> findByMatchId(long id);

}