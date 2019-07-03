package ar.edu.itba.model.DTOs;

import ar.edu.itba.model.League;

import java.util.List;
import java.util.Map;

public class LeagueDTO {

    private Long id;
    private String name;
    private int prize;
    private Integer matchesToPlay, matchesPlayed;
    private List<Map.Entry<String,Integer>> teams;

    public LeagueDTO(League league, Integer matchesToPlay, Integer matchesPlayed, List<Map.Entry<String, Integer>> teams) {
        this.id = league.getId();
        this.name = league.getName();
        this.prize = league.getPrize();
        this.matchesToPlay = matchesToPlay;
        this.matchesPlayed = matchesPlayed;
        this.teams = teams;
    }

    public Integer getMatchesToPlay() {
        return matchesToPlay;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public List<Map.Entry<String, Integer>> getTeams() {
        return teams;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrize() {
        return prize;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrize(int prize) {
        this.prize = prize;
    }

    public void setMatchesToPlay(Integer matchesToPlay) {
        this.matchesToPlay = matchesToPlay;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public void setTeams(List<Map.Entry<String, Integer>> teams) {
        this.teams = teams;
    }
}
