package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class BankLoanTest {

    private final int months = 12, amount = 1200;
    private final double interest = 3.5;

    private BankLoan bLoan;

    @Before
    public void setUp(){
        bLoan = new BankLoan(months, amount, interest);
    }

    @Test
    public void MonthlyAmountTest(){
        assertTrue(bLoan.getMonthlyAmount() == amount * interest / months);
    }

    @Test
    public void RemainingMonthsTest(){
        final int decreaseAmount = 5;

        for(int i = 0; i < decreaseAmount ;i++) {
            bLoan.decreaseMonth();
        }

        assertTrue(bLoan.getRemainingMonths() == months - decreaseAmount);
    }

}