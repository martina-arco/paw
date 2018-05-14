package ar.edu.itba.model;

import java.util.LinkedList;
import java.util.List;

@Deprecated
public class MatchDay {
    private long id;
    private List<Long> matchesIds;
    private List<Match> matches;
    private int week;

    public MatchDay(long id, int week, List<Match> matches) {
        this.id = id;
        this.matches = matches;
        this.week = week;
    }

    public MatchDay(long id, List<Long> matchesIds, int week) {
        this.id = id;
        this.matchesIds = matchesIds;
        this.week = week;
    }

    public List<Long> getMatchesIds() {
        if(matches != null) {
            List<Long> ids = new LinkedList<>();
            for (Match m : matches)
                ids.add(m.getId());
            return ids;
        }
        return matchesIds;
    }

    public List<Match> getMatches() {
        return matches;
    }

    public void setMatches(List<Match> matches) {
        this.matches = matches;
    }

    public int getWeek() {
        return week;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
