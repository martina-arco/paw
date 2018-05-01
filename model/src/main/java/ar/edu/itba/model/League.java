package ar.edu.itba.model;

import com.sun.org.apache.xerces.internal.impl.xpath.regex.Match;

import java.util.List;

public class League {
    private List<MatchDay> fixture;
    private MatchDay currentMatchDate;

    public League(List<MatchDay> fixture, MatchDay currentMatchDate) {
        this.fixture = fixture;
        this.currentMatchDate = currentMatchDate;
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
}
