package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

public class StadiumTest {

    private final Integer testConst = 30;
    private final static String testName = "Test";
    private Stadium stadium;
    private Team team = mock(Team.class);

    @Before
    public void setUp(){
        stadium = new Stadium(0, testName, team, 0, 0, 0, 0, 0, 0 );
    }

    @Test
    public void addLowSeats() {
        stadium.addSeats(Stadium.SeatType.LOW,testConst);
        assertEquals((int) testConst, stadium.getLowClass());
    }

    @Test
    public void addMediumSeats() {
        stadium.addSeats(Stadium.SeatType.MEDIUM,testConst);
        assertEquals((int) testConst, stadium.getMediumClass());
    }

    @Test
    public void addHighSeats() {
        stadium.addSeats(Stadium.SeatType.HIGH,testConst);
        assertEquals((int) testConst, stadium.getHighClass());
    }
}