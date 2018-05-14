package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

/**
 * Created by martina on 30/04/2018.
 */
public class MatchTest {

    private Match match1;
    private final Team t1 = mock(Team.class), t2 = mock(Team.class);

    @Before
    public void setUp(){
        match1 = new Match(0, null, null, null, new Date(), 0,0,0,0, null, false, new LinkedList<Event>());

        match1.addHomeScore(3);
        match1.addAwayScore(2);
        match1.setStats(new LinkedList<PlayerStats>());
        match1.setEvents(new LinkedList<Event>());
    }

    @Test
    public void addEvent() {
        Event e = mock(Event.class);
        PlayerStats stat = mock(PlayerStats.class);

        match1.addEvent(e);
        match1.addStats(stat);

        assertTrue(match1.getEvents().get(0).equals(e));
        assertTrue(match1.getStats().get(0).equals(stat));
    }

    @Test
    public void addScore() {
        assertTrue(match1.getHomeScore() == 3);
        assertTrue(match1.getAwayScore() == 2);
    }

    @Test
    public void finish() {
        match1.finish();

        assertTrue(match1.isPlayed());
        assertTrue(match1.getHomePoints() == 3);
        assertTrue(match1.getAwayPoints() == 0);
    }

}
