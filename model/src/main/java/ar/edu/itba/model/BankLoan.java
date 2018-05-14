package ar.edu.itba.model;

public class BankLoan {

    private int remainingMonths, monthlyAmount;
    private final double interest;

    public BankLoan(final int months, final int amount, final double interest){
        if(!isValid(months, amount, interest))
            throw new IllegalArgumentException();
        this.remainingMonths = months;
        this.monthlyAmount = (int) ((int) amount*interest/months);
        this.interest = interest;
    }

    private boolean isValid(final int months, final int amount, final double interest){
        return months > 0 && amount > 0 && interest > 0;
    }

    public int decreaseMonth(){
        if(remainingMonths <= 0)
            return 0;
        remainingMonths--;
        return remainingMonths;
    }

    public Double getInterest() {
        return interest;
    }

    public int getMonthlyAmount() {
        return monthlyAmount;
    }

    public int getRemainingMonths() {
        return remainingMonths;
    }
}
