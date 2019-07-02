package ar.edu.itba.model.DTOs;

import ar.edu.itba.model.Receipt;
import ar.edu.itba.model.Stadium;

import java.util.Map;

public class EconomyDTO {

    private int income, expense, money;
    private Map<Receipt.Type, Integer> summary;
    private Stadium stadium;

    public EconomyDTO(int income, int expense, int money, Map<Receipt.Type, Integer> summary, Stadium stadium) {
        this.income = income;
        this.expense = expense;
        this.money = money;
        this.summary = summary;
        this.stadium = stadium;
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

    public Stadium getStadium() {
        return stadium;
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

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }
}
