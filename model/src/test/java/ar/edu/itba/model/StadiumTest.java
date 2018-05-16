package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class StadiumTest {

    private final Integer testConst = 30;
    private final String testName = "Test";
    private Stadium stadium;
    private Team team = mock(Team.class);

    @Before
    public void setUp(){
        stadium = new Stadium(0, testName, team, 0, 0, 0, 0, 0, 0 );
    }

    @Test
    public void addLowSeats() {
        stadium.addSeats(Stadium.SeatType.LOW,testConst);
        assertTrue(stadium.getLowClass() == testConst);
    }

    @Test
    public void addMediumSeats() {
        stadium.addSeats(Stadium.SeatType.MEDIUM,testConst);
        assertTrue(stadium.getMediumClass() == testConst);
    }

    @Test
    public void addHighSeats() {
        stadium.addSeats(Stadium.SeatType.HIGH,testConst);
        assertTrue(stadium.getHighClass() == testConst);
    }
}