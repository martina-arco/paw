package ar.edu.itba.model;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private long id;
    private String name;
    private League league;
    private Stadium stadium;
    private Formation formation;
    private List<Player> players;
    private YouthAcademy youthAcademy;
    private Integer fanTrust, boardTrust;
    private List<Receipt> finance;
    private List<BankLoan> loans;
    private Integer money;

    public Team(long id, String name, League league, Stadium stadium, Formation formation, List<Player> players, YouthAcademy youthAcademy, Integer fanTrust, Integer boardTrust, Integer money) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.stadium = stadium;
        this.formation = formation;
        this.players = players;
        this.youthAcademy = youthAcademy;
        this.fanTrust = fanTrust;
        this.boardTrust = boardTrust;
        this.finance = new ArrayList<Receipt>();
        this.loans = new ArrayList<BankLoan>();
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Stadium getStadium() {
        return stadium;
    }

    public void setStadium(Stadium stadium) {
        this.stadium = stadium;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public YouthAcademy getYouthAcademy() {
        return youthAcademy;
    }

    public void setYouthAcademy(YouthAcademy youthAcademy) {
        this.youthAcademy = youthAcademy;
    }

    public Integer getFanTrust() {
        return fanTrust;
    }

    public void setFanTrust(Integer fanTrust) {
        this.fanTrust = fanTrust;
    }

    public Integer getBoardTrust() {
        return boardTrust;
    }

    public void setBoardTrust(Integer boardTrust) {
        this.boardTrust = boardTrust;
    }

    public List<Receipt> getFinance() {
        return finance;
    }

    public void setFinance(List<Receipt> finance) {
        this.finance = finance;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void addReceipt(Receipt r) {
        finance.add(r);
    }
}
