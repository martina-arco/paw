package ar.edu.itba.model;

import java.util.List;

public class MatchDay {
    private long id;
    private List<Match> matches;
    private Integer week;

    public MatchDay(long id, List<Match> matches, Integer week) {
        this.id = id;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
