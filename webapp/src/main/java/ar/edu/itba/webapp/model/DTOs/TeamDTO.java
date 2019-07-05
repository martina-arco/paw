package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Team;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.stream.Collectors;

@XmlRootElement
public class TeamDTO {

    private long id;
    private String name;
    private LeagueShortDTO league;
    private StadiumDTO stadium;
    private String formation;
    private List<PlayerDTO> players;
    private int fanCount, fanTrust, boardTrust, money;
    private String finance;

    public TeamDTO(Team team) {
        this.id = team.getId();
        this.name = team.getName();
        this.league = new LeagueShortDTO(team.getLeague());
        this.stadium = new StadiumDTO(team.getStadium());
        this.formation = "/teams/"+this.id+"/formation";
        this.players = team.getPlayers().stream().map(PlayerDTO::new).collect(Collectors.toList());
        this.fanCount = team.getFanCount();
        this.fanTrust = team.getFanTrust();
        this.boardTrust = team.getBoardTrust();
        this.money = team.getMoney();
        this.finance = "/teams/"+this.id+"/finance";
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LeagueShortDTO getLeague() {
        return league;
    }

    public void setLeague(LeagueShortDTO league) {
        this.league = league;
    }

    public StadiumDTO getStadium() {
        return stadium;
    }

    public void setStadium(StadiumDTO stadium) {
        this.stadium = stadium;
    }

    public List<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(List<PlayerDTO> players) {
        this.players = players;
    }

    public int getFanCount() {
        return fanCount;
    }

    public void setFanCount(int fanCount) {
        this.fanCount = fanCount;
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

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getFormation() {
        return formation;
    }

    public void setFormation(String formation) {
        this.formation = formation;
    }

    public String getFinance() {
        return finance;
    }

    public void setFinance(String finance) {
        this.finance = finance;
    }
}