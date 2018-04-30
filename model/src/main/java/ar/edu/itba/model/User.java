package ar.edu.itba.model;

public class User {

    private String username;
    private String password;
    private long id;

    public User(String username, String password, long id) {
        this.username = username;
        this.id = id;
        this.password = password;
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
}