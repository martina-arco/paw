package ar.edu.itba.model.DTOs;

import ar.edu.itba.model.Event;
import ar.edu.itba.model.Match;

import java.util.ArrayList;
import java.util.List;

public class MatchDTO {
    private final Long matchId;
    private final String home, away, stadium;
    private final int homeScore, awayScore;
    private final List<EventDTO> events;

    public MatchDTO(Match match){
        this(match.getId(), match.getHome().getName(), match.getAway().getName(), match.getHome().getStadium().getName(), match.getHomeScore(), match.getAwayScore(), match.getEvents());
    }

    public MatchDTO(Long matchId, String home, String away, String stadium, int homeScore, int awayScore, List<Event> events) {
        this.matchId = matchId;
        this.home = home;
        this.away = away;
        this.stadium = stadium;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.events = new ArrayList<>();
        for(Event event : events){
            this.events.add(new EventDTO(event));
        }
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public List<EventDTO> getEvents() {
        return events;
    }

    public String getAway() {
        return away;
    }

    public String getHome() {
        return home;
    }

    public String getStadium() {
        return stadium;
    }

    public Long getMatchId() {
        return matchId;
    }
}
