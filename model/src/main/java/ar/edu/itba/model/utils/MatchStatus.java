package ar.edu.itba.model.utils;

import ar.edu.itba.model.Event;

import java.util.ArrayList;
import java.util.List;

public class MatchStatus {
    private int homeScore, awayScore, minute;
    private List<Event> events;

    public MatchStatus(int homeScore, int awayScore, List<Event> events){
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.events = events;
    }

    public MatchStatus cloneAndFlush(){
        List<Event> aux = new ArrayList<>();
        aux.addAll(events);
        events = new ArrayList<>();
        return new MatchStatus(homeScore,awayScore,aux);
    }

    public MatchStatus filterEvents(){
        List<Event> toFilter = new ArrayList<>();

        for(Event event : events){
            Event.Type type = event.getType();
            if(type.equals(Event.Type.SCORE) || type.equals(Event.Type.RED_CARD) || type.equals(Event.Type.YELLOW_CARD) || type.equals(Event.Type.SUBSTITUTE))
                toFilter.add(event);
        }

        events.removeAll(toFilter);
        return this;
    }

    public int getMinute() {
        return minute;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setMinute(int minute){
        this.minute = minute;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }
}
