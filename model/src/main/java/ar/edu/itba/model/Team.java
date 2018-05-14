package ar.edu.itba.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Team {
    private long id, leagueId, stadiumId, formationId;
    private String name;
    private League league;
    private Stadium stadium;
    private Formation formation;
    private List<Long> playersIds;
    private List<Player> players;
    private List<Long> youthAcademyIds;
    private List<Player> youthAcademy;
    private Integer fanTrust, boardTrust;
    private List<Long> financeIds;
    private List<Receipt> finance;
    private List<Long> loansIds;
    private List<BankLoan> loans;
    private Integer money;

    public Team(long id, String name, League league, Stadium stadium, Formation formation, List<Player> players,
                List<Player> youthAcademy, Integer fanTrust, Integer boardTrust, List<Receipt> finance,
                List<BankLoan> loans, Integer money) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.stadium = stadium;
        this.formation = formation;
        this.players = players;
        this.youthAcademy = youthAcademy;
        this.fanTrust = fanTrust;
        this.boardTrust = boardTrust;
        this.finance = finance;
        this.loans = loans;
        this.money = money;
    }

    public Team(long id, long leagueId, long stadiumId, long formationId, String name, List<Long> playersIds,
                List<Long> youthAcademyIds, Integer fanTrust, Integer boardTrust, List<Long> financeIds,
                List<Long> loansIds, Integer money) {
        this.id = id;
        this.leagueId = leagueId;
        this.stadiumId = stadiumId;
        this.formationId = formationId;
        this.name = name;
        this.playersIds = playersIds;
        this.youthAcademyIds = youthAcademyIds;
        this.fanTrust = fanTrust;
        this.boardTrust = boardTrust;
        this.financeIds = financeIds;
        this.loansIds = loansIds;
        this.money = money;
    }

    public long getLeagueId() {
        if(league != null)
            return league.getId();
        return leagueId;
    }

    public long getStadiumId() {
        if(stadium != null)
            return stadium.getId();
        return stadiumId;
    }

    public long getFormationId() {
        if(formation != null)
            return formation.getId();
        return formationId;
    }

    public List<Long> getPlayersIds() {
        if(players != null) {
            List<Long> ids = new LinkedList<>();
            for (Player p : players)
                ids.add(p.getId());
            return ids;
        }
        return playersIds;
    }

    public List<Long> getYouthAcademyIds() {
        if(youthAcademy != null) {
            List<Long> ids = new LinkedList<>();
            for (Player p : youthAcademy)
                ids.add(p.getId());
            return ids;
        }
        return youthAcademyIds;
    }

    public List<Long> getFinanceIds() {
        if(finance != null) {
            List<Long> ids = new LinkedList<>();
            for (Receipt r : finance)
                ids.add(r.getId());
            return ids;
        }
        return financeIds;
    }

    /*public List<Long> getLoansIds() {
        if(loans != null) {
            List<Long> ids = new LinkedList<>();
            for (BankLoan l : loans)
                ids.add(l.getId());
            return ids;
        }
        return loansIds;
    }

    public List<BankLoan> getLoans() {
        return loans;
    }*/

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

    public List<Player> getYouthAcademy() {
        return youthAcademy;
    }

    public void setYouthAcademy(List<Player> youthAcademy) {
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
