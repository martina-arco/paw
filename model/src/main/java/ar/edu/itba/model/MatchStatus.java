package ar.edu.itba.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MatchStatus {
    private int homeScore, awayScore, minute;
    private List<Event> events;

    public MatchStatus(int homeScore, int awayScore, int minute, List<Event> events){
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.minute = minute;
        this.events = events;
    }

    public MatchStatus cloneAndFlush(){
        List<Event> aux = new ArrayList<>(events);
        events = new ArrayList<>();
        return new MatchStatus(homeScore,awayScore,minute,aux);
    }

//    TODO: sacar si no se usa
//    public MatchStatus filterEvents(){
//        List<Event> toFilter = new ArrayList<>();
//
//        for(Event event : events){
//            Event.Type type = event.getType();
//            if(!type.equals(Event.Type.HOMESCORE) || !(type.equals(Event.Type.AWAYSCORE) ||
//                    type.equals(Event.Type.RED_CARD) || type.equals(Event.Type.YELLOW_CARD) ||
//                    type.equals(Event.Type.SUBSTITUTE)))
//                toFilter.add(event);
//        }
//
//        events.removeAll(toFilter);
//        return this;
//    }

    public List<Event> getEventsByMinute(int minute){
        List<Event> aux = new ArrayList<>(events);
        return aux.stream().filter(event -> event.getMinute() == minute).collect(Collectors.toList());
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
