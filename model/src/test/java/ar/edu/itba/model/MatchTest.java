package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;

public class MatchTest {

    private Match match1;
    private final Team t1 = mock(Team.class), t2 = mock(Team.class);

    @Before
    public void setUp(){
        match1 = new Match(0, null, null, null, new Date(), 0,0,0,0, null, false, new LinkedList<Event>());

        match1.addHomeScore(3);
        match1.addAwayScore(2);
        match1.setStats(new LinkedList<>());
        match1.setEvents(new LinkedList<>());
    }

    @Test
    public void addEvent() {
        Event e = mock(Event.class);
        PlayerStats stat = mock(PlayerStats.class);

        match1.addEvent(e);
        match1.addStats(stat);

        assertEquals(e, match1.getEvents().get(0));
        assertEquals(stat, match1.getStats().get(0));
    }

    @Test
    public void addScore() {
        assertEquals(3, match1.getHomeScore());
        assertEquals(2, match1.getAwayScore());
    }

    @Test
    public void finish() {
        match1.finish();

        assertTrue(match1.isPlayed());
        assertEquals(3, match1.getHomePoints());
        assertEquals(0, match1.getAwayPoints());
    }

}
