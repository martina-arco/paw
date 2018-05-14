package ar.edu.itba.model;

public class User {

    private long id;
    private String username;
    private String password;
    private String mail;
    private long teamId;
    private Team team;

    public User(long id, String username, String password, String mail, Team team) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.team = team;
    }

    public User(long id, String username, String password, String mail, long teamId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.teamId = teamId;
    }

    public long getTeamId() {
        if(team != null)
            return team.getId();
        return teamId;
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