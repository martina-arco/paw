package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.League;

import java.util.List;
import java.util.Map;

public class LeagueShortDTO {

    private long id;
    private String name;
    private int prize;
    private long userId;

    public LeagueShortDTO(){}

    public LeagueShortDTO(League league) {
        this.id = league.getId();
        this.name = league.getName();
        this.prize = league.getPrize();
        this.userId = league.getUserId();
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

    public int getPrize() {
        return prize;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
