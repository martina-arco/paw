package ar.edu.itba.model.utils;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    private final int day = 1, month = 5, year = 2018;
    private Date testDate;

    @Before
    public void setUp(){
        testDate = new Date(day, month, year);
    }

    @Test
    public void printTest(){
        assertEquals("01/05/2018", testDate.toString());
    }

    @Test
    public void timeTravelTest() {
        Date aux = Date.timeTravel(Date.Unit.DAY, -1, testDate);
        assertEquals("31/04/2018", aux.toString());
    }

    @Test
    public void compare(){
        Date aux = new Date(day + 1, month, year);
        assertTrue(testDate.compareTo(aux) < 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidArgs(){
        new Date(35,3,1999);
    }
}