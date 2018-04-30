package ar.edu.itba.model;

public class User {

    private String username;
    private String password;
    private long userId;

    public User(String username, long userId) {
        this.username = username;
        this.userId = userId;
        this.password = "";
    }

    public User(String username, String password) {
        this.username = username;
        this.userId = 0;
        this.password = password;
    }

    public User(String username, String password, long id) {
        this.username = username;
        this.userId = id;
        this.password = password;
    }

    public long getId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}