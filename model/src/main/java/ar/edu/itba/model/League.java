package ar.edu.itba.model;

import ar.edu.itba.model.utils.*;

import java.util.*;
import java.util.Date;

public class League {
    private long id;
    private String name;
    private Map<Date, List<Match>> fixture;
    private int prize;

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

    @Deprecated
    public Map<Date, List<Long>> getFixtureIds() {
        if(fixture != null) {
            Map<Date, List<Long>> map = new HashMap<>();
            List<Long> ids;
            for (Map.Entry<Date, List<Match>> e : fixture.entrySet()) {
                ids = new LinkedList<>();
                for (Match m : e.getValue()) {
                    ids.add(m.getId());
                }
                map.put(e.getKey(), ids);
            }
            return map;
        }
        return null;
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
}
