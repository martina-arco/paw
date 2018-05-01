package ar.edu.itba.model;

import java.util.List;

public class MatchDay {
    private List<Match> matches;
    private Integer week;

    public MatchDay(List<Match> matches, Integer week) {
        this.matches = matches;
        this.week = week;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public Integer getWeek() {
        return week;
    }

    public void setWeek(Integer week) {
        this.week = week;
    }
}
