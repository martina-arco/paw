package ar.edu.itba.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_userid_seq")
    @SequenceGenerator(sequenceName = "users_userid_seq", name = "users_userid_seq", allocationSize = 1)
    @Column(name = "userid")
    private long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String mail;

    @Transient
    private long teamId;

    @OneToOne
    @JoinColumn(name = "team")
    private Team team;

    @Column(nullable = false)
    private Date currentDay;

    public User(){}

    public User(String username, String password, String mail, Team team, Date currentDay) {
        this.username = username;
        this.password = password;
        this.mail = mail;
        this.team = team;
        this.currentDay = currentDay;
    }

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