package ar.edu.itba.model;

import java.util.Date;

@Deprecated
public class Contract {
    private Team team;
    private Player player;
    private int salary;
    private Date expiration;
    private long id, playerId, teamId;

    public Contract(long id, int salary, Date expiration, Player player, Team team) {
        this.salary = salary;
        this.team = team;
        this.player = player;
        this.expiration = expiration;
        this.id = id;
    }

    public Contract(long id, int salary, Date expiration, long playerId, long teamId) {
        this.salary = salary;
        this.expiration = expiration;
        this.id = id;
        this.playerId = playerId;
        this.teamId = teamId;
    }

    public long getPlayerId() {
        if(player !=  null)
            return player.getId();
        return playerId;
    }

    public long getTeamId() {
        if(team != null)
            return team.getId();
        return teamId;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return player;
    }

    //Esto va en Service? @lemery
    public boolean isDone(Date date){
        return expiration.compareTo(date) < 0;
    }

    public Date getExpiration() {
        return expiration;
    }

    public int getSalary() {
        return salary;
    }

    public Team getTeam() {
        return team;
    }

    public long getId() {
        return id;
    }
}
