package ar.edu.itba.model;

import java.util.List;

public class League {
    private List<MatchDay> fixture;
    private MatchDay currentMatchDate;
    private long id;

    public League(List<MatchDay> fixture, MatchDay currentMatchDate, long id) {
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
}
