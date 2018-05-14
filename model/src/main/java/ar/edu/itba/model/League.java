package ar.edu.itba.model;

import java.util.LinkedList;
import java.util.List;

public class League {
    private long id;
    private String name;
    private List<Long> fixtureIds;
    private List<MatchDay> fixture;
    private long currentMatchDayId;
    private MatchDay currentMatchDay;


    public League(long id, String name, List<MatchDay> fixture, MatchDay currentMatchDate) {
        this.name = name;
        this.fixture = fixture;
        this.currentMatchDay = currentMatchDate;
        this.id = id;
    }

    public League(long id, String name, List<Long> fixtureIds, long currentMatchDateId) {
        this.id = id;
        this.name = name;
        this.fixtureIds = fixtureIds;
        this.currentMatchDayId = currentMatchDateId;
    }

    public List<Long> getFixtureIds() {
        if(fixture != null) {
            List<Long> ids = new LinkedList<>();
            for (MatchDay m : fixture)
                ids.add(m.getId());
            return ids;
        }
        return fixtureIds;
    }

    public long getCurrentMatchDayId() {
        if(currentMatchDay != null)
            return currentMatchDay.getId();
        return currentMatchDayId;
    }

    public List<MatchDay> getFixture() {
        return fixture;
    }

    public void setFixture(List<MatchDay> fixture) {
        this.fixture = fixture;
    }

    public MatchDay getCurrentMatchDay() {
        return currentMatchDay;
    }

    public void setCurrentMatchDay(MatchDay currentMatchDay) {
        this.currentMatchDay = currentMatchDay;
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
