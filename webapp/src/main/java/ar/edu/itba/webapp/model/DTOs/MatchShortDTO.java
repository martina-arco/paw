package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;

import java.util.ArrayList;
import java.util.List;

public class MatchShortDTO {
    private long id;
    private TeamShortDTO home, away;
    private StadiumDTO stadium;
    private int homeScore, awayScore;
    private String events;

    public MatchShortDTO(){}

    public MatchShortDTO(Match match){
        this(match.getId(), match.getHome(), match.getAway(), match.getHome().getStadium(), match.getHomeScore(), match.getAwayScore());
    }

    public MatchShortDTO(long matchId, Team home, Team away, Stadium stadium, int homeScore, int awayScore) {
        this.id = matchId;
        this.home = new TeamShortDTO(home);
        this.away = new TeamShortDTO(away);
        this.stadium = new StadiumDTO(stadium);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.events = "/matches/"+this.id+"/events";
    }

    public long getId() {
        return id;
    }

    public TeamShortDTO getHome() {
        return home;
    }

    public TeamShortDTO getAway() {
        return away;
    }

    public StadiumDTO getStadium() {
        return stadium;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public String getEvents() {
        return events;
    }
}
