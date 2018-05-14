package ar.edu.itba.model;

import java.util.Date;

public class User {

    private long id;
    private String username;
    private String password;
    private String mail;
    private long teamId;
    private Team team;
    private Date currentDay;

    public User(long id, String username, String password, String mail, Team team, Date currentDay) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.team = team;
        this.currentDay = currentDay;
    }

    public User(long id, String username, String password, String mail, long teamId, Date currentDay) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.teamId = teamId;
        this.currentDay = currentDay;
    }

    public long getTeamId() {
        if(team != null)
            return team.getId();
        return teamId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getCurrentDay() {
        return currentDay;
    }

    public void setCurrentDay(Date currentDay) {
        this.currentDay = currentDay;
    }

    public String getMail() {
        return mail;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}