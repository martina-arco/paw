package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Receipt;

import java.util.Map;

public class EconomyDTO {

    private int income, expense, money;
    private Map<Receipt.Type, Integer> summary;

    public EconomyDTO(){}

    public EconomyDTO(int income, int expense, int money, Map<Receipt.Type, Integer> summary) {
        this.income = income;
        this.expense = expense;
        this.money = money;
        this.summary = summary;
    }

    public int getIncome() {
        return income;
    }

    public int getExpense() {
        return expense;
    }

    public int getMoney() {
        return money;
    }

    public Map<Receipt.Type, Integer> getSummary() {
        return summary;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public void setExpense(int expense) {
        this.expense = expense;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setSummary(Map<Receipt.Type, Integer> summary) {
        this.summary = summary;
    }

}
