package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class PlayerStatsTest {

    private final Integer testConst = 10;
    private final String testName = "Test";
    private PlayerStats stats;
    private int id = 0;

    @Before
    public void setUp(){
        Match m = mock(Match.class);
        Player p = mock(Player.class);
        stats = new PlayerStats(id, m, p, 0,0,0,0,0,0,0,0);
    }

    @Test
    public void addTackles() {
        stats.addTackle(testConst);
        assertEquals((int) testConst, stats.getTackles());

        stats.addTackle(testConst);
        assertEquals(2 * testConst, stats.getTackles());
    }

    @Test
    public void addAssists() {
        stats.addAssist(testConst);
        assertEquals((int) testConst, stats.getAssists());

        stats.addAssist(testConst);
        assertEquals(2 * testConst, stats.getAssists());
    }

    @Test
    public void addSaves() {
        stats.addSave(testConst);
        assertEquals(stats.getSaves(), (int) testConst);

        stats.addSave(testConst);
        assertEquals(2 * testConst, stats.getSaves());
    }

    @Test
    public void addPasses() {
        stats.addPass(testConst);
        assertEquals((int) testConst, stats.getPasses());

        stats.addPass(testConst);
        assertEquals(2 * testConst, stats.getPasses());
    }

    @Test
    public void addYellowCard() {
        stats.addYellowCard();
        assertEquals(1, stats.getYellowCards());

        stats.addYellowCard();
        assertEquals(2, stats.getYellowCards());
    }

    @Test
    public void addRedCard() {
        stats.addRedCard();
        assertEquals(1, stats.getRedCards());

        stats.addRedCard();
        assertEquals(2, stats.getRedCards());
    }

    @Test
    public void addScore() {
        stats.addScore();
        assertEquals(1, stats.getScores());

        stats.addScore();
        assertEquals(2, stats.getScores());
    }

}
