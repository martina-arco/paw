package ar.edu.itba.model;

import com.sun.istack.internal.Nullable;
import org.hibernate.annotations.JoinFormula;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "team")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_teamid_seq")
    @SequenceGenerator(sequenceName = "team_teamid_seq", name = "team_teamid_seq", allocationSize = 1)
    @Column(name = "teamid")
    private long id;

    @Transient
    private long leagueId, stadiumId, formationId;

    @Column(nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "league")
    private League league;

    @OneToOne(mappedBy = "team")
    private Stadium stadium;

    @OneToOne
    @JoinColumn(name = "formation")
    private Formation formation;

    @OneToMany(mappedBy = "team")
    @Where(clause = "youth = FALSE")
    private List<Player> players;

    @OneToMany(mappedBy = "team")
    @Where(clause = "youth = TRUE")
    private List<Player> youthAcademy;

    @Column(nullable = false)
    private int fanCount, fanTrust, boardTrust, money;

    @OneToMany(mappedBy = "team")
    private List<Receipt> finance;

    @Transient
    private List<BankLoan> loans;

    public Team(){}

    public Team(String name, League league, Stadium stadium, Formation formation, List<Player> players,
                List<Player> youthAcademy, int fanCount, int fanTrust, int boardTrust, int money, List<Receipt> finance) {
        this.name = name;
        this.league = league;
        this.stadium = stadium;
        this.formation = formation;
        this.players = players;
        this.youthAcademy = youthAcademy;
        this.fanCount = fanCount;
        this.fanTrust = fanTrust;
        this.boardTrust = boardTrust;
        this.money = money;
        this.finance = finance;
    }

    public Team(long id, String name, League league, Stadium stadium, Formation formation, List<Player> players,
                List<Player> youthAcademy, int fanCount, int fanTrust, int boardTrust, List<Receipt> finance,
                List<BankLoan> loans, int money) {
        this.id = id;
        this.name = name;
        this.league = league;
        this.stadium = stadium;
        this.formation = formation;
        this.players = players;
        this.youthAcademy = youthAcademy;
        this.fanCount = fanCount;
        this.fanTrust = fanTrust;
        this.boardTrust = boardTrust;
        this.finance = finance;
        this.loans = loans;
        this.money = money;
    }

    public Team(long id, long leagueId, long formationId, String name, int fanCount, int fanTrust,
                int boardTrust, int money) {
        this.id = id;
        this.leagueId = leagueId;
        this.formationId = formationId;
        this.name = name;
        this.fanCount = fanCount;
        this.fanTrust = fanTrust;
        this.boardTrust = boardTrust;
        this.money = money;
    }

    public long getLeagueId() {
        if(league != null)
            return league.getId();
        return leagueId;
    }

    public long getFormationId() {
        if(formation != null)
            return formation.getId();
        return formationId;
    }

    public boolean removePlayer(Player player) {
        return players.remove(player);
    }

    @Deprecated
    public List<Long> getPlayersIds() {
        if(players != null) {
            List<Long> ids = new LinkedList<>();
            for (Player p : players)
                ids.add(p.getId());
            return ids;
        }
        return null;
    }

    @Deprecated
    public List<Long> getYouthAcademyIds() {
        if(youthAcademy != null) {
            List<Long> ids = new LinkedList<>();
            for (Player p : youthAcademy)
                ids.add(p.getId());
            return ids;
        }
        return null;
    }

    @Deprecated
    public List<Long> getFinanceIds() {
        if(finance != null) {
            List<Long> ids = new LinkedList<>();
            for (Receipt r : finance)
                ids.add(r.getId());
            return ids;
        }
        return null;
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

    public int getSalaries() {
        int result = 0;

        for (Player player : players) {
            result += player.getSalary();
        }

        return result;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void addMoney(int amount) {
        money += amount;
    }

    public int getFanCount() {
        return fanCount;
    }

    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
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

    public List<Player> getYouthAcademy() {
        return youthAcademy;
    }

    public void setYouthAcademy(List<Player> youthAcademy) {
        this.youthAcademy = youthAcademy;
    }

    public int getFanTrust() {
        return fanTrust;
    }

    public void setFanTrust(int fanTrust) {
        this.fanTrust = fanTrust;
    }

    public int getBoardTrust() {
        return boardTrust;
    }

    public void setBoardTrust(int boardTrust) {
        this.boardTrust = boardTrust;
    }

    public List<Receipt> getFinance() {
        return finance;
    }

    public void setFinance(List<Receipt> finance) {
        this.finance = finance;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
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
