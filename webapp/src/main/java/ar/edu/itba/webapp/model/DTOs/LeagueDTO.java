package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.League;
import ar.edu.itba.model.Match;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LeagueDTO {

    private long id;
    private String name;
    private int prize;
    private Integer matchesToPlay, matchesPlayed;
    private List<Map.Entry<String,Integer>> teams;
    private List<MatchShortDTO> upcomingMatches;

    public LeagueDTO(League league, Integer matchesToPlay, Integer matchesPlayed, List<Map.Entry<String, Integer>> teams,
                     List<Match> upcomingMatches) {
        this.id = league.getId();
        this.name = league.getName();
        this.prize = league.getPrize();
        this.matchesToPlay = matchesToPlay;
        this.matchesPlayed = matchesPlayed;
        this.teams = teams;
        this.upcomingMatches = upcomingMatches.parallelStream().map(MatchShortDTO::new).collect(Collectors.toList());
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

    public Integer getMatchesToPlay() {
        return matchesToPlay;
    }

    public void setMatchesToPlay(Integer matchesToPlay) {
        this.matchesToPlay = matchesToPlay;
    }

    public Integer getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(Integer matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public List<Map.Entry<String, Integer>> getTeams() {
        return teams;
    }

    public void setTeams(List<Map.Entry<String, Integer>> teams) {
        this.teams = teams;
    }

    public List<MatchShortDTO> getUpcomingMatches() {
        return upcomingMatches;
    }

    public void setUpcomingMatches(List<MatchShortDTO> upcomingMatches) {
        this.upcomingMatches = upcomingMatches;
    }
}
