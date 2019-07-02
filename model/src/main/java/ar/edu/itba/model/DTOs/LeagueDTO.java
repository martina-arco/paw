package ar.edu.itba.model.DTOs;

import ar.edu.itba.model.League;

import java.util.List;
import java.util.Map;

public class LeagueDTO {

    private League league;
    private Integer matchesToPlay, matchesPlayed;
    private List<Map.Entry<String,Integer>> teams;

    public LeagueDTO(League league, Integer matchesToPlay, Integer matchesPlayed, List<Map.Entry<String, Integer>> teams) {
        this.league = league;
        this.matchesToPlay = matchesToPlay;
        this.matchesPlayed = matchesPlayed;
        this.teams = teams;
    }

    public League getLeague() {
        return league;
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

    public void setLeague(League league) {
        this.league = league;
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
