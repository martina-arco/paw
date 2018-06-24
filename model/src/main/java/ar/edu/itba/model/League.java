package ar.edu.itba.model;

import ar.edu.itba.model.utils.*;

import javax.persistence.*;
import java.util.*;
import java.util.Date;

@Entity
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "league_leagueid_seq")
    @SequenceGenerator(sequenceName = "league_leagueid_seq", name = "league_leagueid_seq", allocationSize = 1)
    @Column(name = "leagueid")
    private long id;

    @Column(nullable = false)
    private String name;

    @Transient
    private Map<Date, List<Match>> fixture;

    @Column(nullable = false)
    private int prize;

    @ManyToOne
    @JoinColumn(name = "userid", nullable = false)
    private User user;

    public League(){}

    public League(String name, int prize, User user) {
        this.name = name;
        this.prize = prize;
        this.user = user;
    }

    public League(long id, String name, Map<Date, List<Match>> fixture, int prize) {
        this.id = id;
        this.name = name;
        this.fixture = fixture;
        this.prize = prize;
    }

    public League(long id, String name, int prize) {
        this.id = id;
        this.name = name;
        this.prize = prize;
    }

    public List<Match> getMatches(Date date) {
        return fixture.get(date);
    }

    public int getPrize() {
        return prize;
    }

    public Map<Date, List<Match>> getFixture() {
        return fixture;
    }

    public void setFixture(Map<Date, List<Match>> fixture) {
        this.fixture = fixture;
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

    public User getUser() {
        return user;
    }
}
