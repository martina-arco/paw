package ar.edu.itba.model;

import java.util.List;

public class Team {
    private String name;
    private League league;
    private Stadium stadium;
    private Formation formation;
    private YouthAcademy youthAcademy;
    private Integer fanTrust, boardTrust;
    private List<Reciept> finance;
    private Integer money;

    public Team(String name, League league, Stadium stadium, Formation formation, YouthAcademy youthAcademy, Integer fanTrust, Integer boardTrust, List<Reciept> finance, Integer money) {
        this.name = name;
        this.league = league;
        this.stadium = stadium;
        this.formation = formation;
        this.youthAcademy = youthAcademy;
        this.fanTrust = fanTrust;
        this.boardTrust = boardTrust;
        this.finance = finance;
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

    public List<Reciept> getFinance() {
        return finance;
    }

    public void setFinance(List<Reciept> finance) {
        this.finance = finance;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }
}
