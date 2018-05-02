package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static ar.edu.itba.model.EventType.SCORE;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by martina on 30/04/2018.
 */
public class MatchTest {

    private Match match1, match2;
    private final int homeScore = 3, awayScore = 2, homePoints = 3, awayPoints = 0, testConst = 10;
    private final Team t1 = mock(Team.class), t2 = mock(Team.class);
    private final Player p1 = mock(Player.class), p2 = mock(Player.class);
    private final PlayerStats stat1 = mock(PlayerStats.class), stat2 = mock(PlayerStats.class);

    @Before
    public void setUp(){

        HashMap<Player, PlayerStats> map = new HashMap<Player, PlayerStats>();

        when(stat1.getPlayer()).thenReturn(p1);
        when(stat2.getPlayer()).thenReturn(p2);

        when(p1.getTeam()).thenReturn(t1);
        when(p2.getTeam()).thenReturn(t2);

        map.put(p1, stat1);
        map.put(p2, stat2);

        match1 = new Match(0, t1, t2, 0,0,0,0, map, false, new ArrayList<Event>());
        match2 = new Match(0, t1, t2, homeScore, awayScore, homePoints, awayPoints, new HashMap<Player, PlayerStats>(), true, new ArrayList<Event>());
    }

//    @Test
//    public void changeStats() {
//        PlayerStats stat = mock(PlayerStats.class);
//        Player p =  mock(Player.class);
//
//        when(stat.getPlayer()).thenReturn(p);
//
//        match1.createStats(stat);
//        match1.changeStats(p, EventType.ASSIST, testConst);
//
//        assertTrue(stat.getAssists() == testConst);
//    }

    @Test
    public void addScore() {

        match1.addEvent(0, p1, null, SCORE, 64);
        match1.addEvent(0, p2, null, SCORE, 60);

        assertTrue(match1.getHomeScore() == 1);
        assertTrue(match1.getAwayScore() == 1);
    }

}
