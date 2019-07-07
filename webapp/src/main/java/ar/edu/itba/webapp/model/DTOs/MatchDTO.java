package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;
import ar.edu.itba.model.Stadium;
import ar.edu.itba.model.Team;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MatchDTO {
    private long id;
    private TeamShortDTO home, away;
    private StadiumDTO stadium;
    private int homeScore, awayScore;
    private List<EventDTO> events;

    public MatchDTO(){}

    public MatchDTO(Match match){
        this(match.getId(), match.getHome(), match.getAway(), match.getHome().getStadium(), match.getHomeScore(), match.getAwayScore(), match.getEvents());
    }

    public MatchDTO(long matchId, Team home, Team away, Stadium stadium, int homeScore, int awayScore, List<Event> events) {
        this.id = matchId;
        this.home = new TeamShortDTO(home);
        this.away = new TeamShortDTO(away);
        this.stadium = new StadiumDTO(stadium);
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.events = new LinkedList<>();
        for(Event event : events){
            this.events.add(new EventDTO(event));
        }
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

    public List<EventDTO> getEvents() {
        return events;
    }
}
