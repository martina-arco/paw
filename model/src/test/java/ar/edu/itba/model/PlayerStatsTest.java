package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

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
        assertTrue(stats.getTackles() == testConst);

        stats.addTackle(testConst);
        assertTrue(stats.getTackles() == 2*testConst);
    }

    @Test
    public void addAssists() {
        stats.addAssist(testConst);
        assertTrue(stats.getAssists() == testConst);

        stats.addAssist(testConst);
        assertTrue(stats.getAssists() == 2*testConst);
    }

    @Test
    public void addSaves() {
        stats.addSave(testConst);
        assertTrue(stats.getSaves() == testConst);

        stats.addSave(testConst);
        assertTrue(stats.getSaves() == 2*testConst);
    }

    @Test
    public void addPasses() {
        stats.addPass(testConst);
        assertTrue(stats.getPasses() == testConst);

        stats.addPass(testConst);
        assertTrue(stats.getPasses() == 2*testConst);
    }

    @Test
    public void addYellowCard() {
        stats.addYellowCard();
        assertTrue(stats.getYellowCards() == 1);

        stats.addYellowCard();
        assertTrue(stats.getYellowCards() == 2);
    }

    @Test
    public void addRedCard() {
        stats.addRedCard();
        assertTrue(stats.getRedCards() == 1);

        stats.addRedCard();
        assertTrue(stats.getRedCards() == 2);
    }

    @Test
    public void addScore() {
        stats.addScore();
        assertTrue(stats.getScores() == 1);

        stats.addScore();
        assertTrue(stats.getScores() == 2);
    }

}
