package ar.edu.itba.model;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
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
        assertEquals((int)(amount * interest / months), bLoan.getMonthlyAmount());
    }

    @Test
    public void RemainingMonthsTest(){
        final int decreaseAmount = 5;

        for(int i = 0; i < decreaseAmount ;i++) {
            bLoan.decreaseMonth();
        }

        assertEquals(months - decreaseAmount, bLoan.getRemainingMonths());
    }

}