package ar.edu.itba.model;

import java.util.List;

public class League {
    private long id;
    private String name;
    private List<MatchDay> fixture;
    private MatchDay currentMatchDate;


    public League(long id, String name, List<MatchDay> fixture, MatchDay currentMatchDate) {
        this.name = name;
        this.fixture = fixture;
        this.currentMatchDate = currentMatchDate;
        this.id = id;
    }

    public List<MatchDay> getFixture() {
        return fixture;
    }

    public void setFixture(List<MatchDay> fixture) {
        this.fixture = fixture;
    }

    public MatchDay getCurrentMatchDate() {
        return currentMatchDate;
    }

    public void setCurrentMatchDate(MatchDay currentMatchDate) {
        this.currentMatchDate = currentMatchDate;
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
}
