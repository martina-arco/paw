package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StadiumTest {

    private final Integer testConst = 30;
    private final String testName = "Test";
    private Stadium stadium;

    @Before
    public void setUp(){
        stadium = new Stadium(testName);
    }

    @Test
    public void addLowSeats() {
        stadium.addSeats(SeatType.LOW,testConst);
        assertTrue(stadium.getLowClass().equals(testConst));
    }

    @Test
    public void addMediumSeats() {
        stadium.addSeats(SeatType.MEDIUM,testConst);
        assertTrue(stadium.getMediumClass().equals(testConst));
    }

    @Test
    public void addHighSeats() {
        stadium.addSeats(SeatType.HIGH,testConst);
        assertTrue(stadium.getHighClass().equals(testConst));
    }
}