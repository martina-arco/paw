package ar.edu.itba.interfaces.dao;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Player;

import java.util.List;

/**
 * Created by martina on 01/05/2018.
 */
public interface EventDao {

    Event create(Match m, Player p1, Player p2, Event.Type eventType, int minute);
    Event create(long m, long p1, long p2, Event.Type eventType, int minute);
    List<Event> findByMatchId(long id);

}
