package ar.edu.itba.model;

public class BankLoan {

    private int remainingMonths, monthlyAmount;
    private double interest;

    public BankLoan(int months, int amount, double interest){
        this.remainingMonths = months;
        this.monthlyAmount = (int) ((int) amount*interest/months);
        this.interest = interest;
    }

    public Double getInterest() {
        return interest;
    }

    public Integer getMonthlyAmount() {
        return monthlyAmount;
    }

    public Integer getRemainingMonths() {
        return remainingMonths;
    }
}
