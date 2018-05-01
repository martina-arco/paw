package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.EventType;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;

import java.util.List;

/**
 * Created by martina on 01/05/2018.
 */
public interface EventDao {

    Event create(Match m, Player p1, Player p2, EventType eventType, int minute);
    List<Event> findByMatchId(long id);

}
