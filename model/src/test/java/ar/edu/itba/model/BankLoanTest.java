package ar.edu.itba.model;

import org.junit.Test;

public class BankLoanTest {

    private final Integer months = 12, amount = 1200;
    private final double interest = 3.5;

    @Test
    public void BankLoanTest(){
        BankLoan bloan = new BankLoan(months, amount, interest);
    }

}