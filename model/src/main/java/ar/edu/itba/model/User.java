package ar.edu.itba.model;

public class User {

    private String username;
    private String password;
    private long id;
    private Team team;

    public User(String username, String password, long id) {
        this.username = username;
        this.id = id;
        this.password = password;
        this.team = team;
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
}