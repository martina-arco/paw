package ar.edu.itba.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static ar.edu.itba.model.EventType.*;

/**
 * Created by martina on 30/04/2018.
 */

public class Match {

    private Team home, away;
    private int homeScore, awayScore, homePoints, awayPoints;
    private Map<Player, PlayerStats> stats;
    private boolean played;
    private List<Event> events;
    private final long id;

    public Match(long id, Team home, Team away, int homeScore, int awayScore, int homePoints,
                 int awayPoints, Map<Player, PlayerStats> stats, boolean played, List<Event> events) {
        this.home = home;
        this.away = away;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homePoints = homePoints;
        this.awayPoints = awayPoints;
        this.stats = stats;
        this.played = played;
        this.events = events;
        this.id = id;
    }

    public void finish() {
        if(homeScore > awayScore)
            homePoints = 3;
        else if(awayScore > homeScore)
            awayPoints = 3;
        else {
            homePoints = 1;
            awayPoints = 1;
        }

        played = true;
    }

    public void addHomeScore (int amount) {
        homeScore += amount;
    }

    public void addAwayScore(int amount) {
        awayScore += amount;
    }

    public void addStats(PlayerStats stat) {
        stats.put(stat.getPlayer(), stat);
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public Team getHome() {
        return home;
    }

    public Team getAway() {
        return away;
    }

    public Integer getHomeScore() {
        return homeScore;
    }

    public Integer getAwayScore() {
        return awayScore;
    }

    public Integer getHomePoints() {
        return homePoints;
    }

    public Integer getAwayPoints() {
        return awayPoints;
    }

    public List<PlayerStats> getStats() {
        return new ArrayList<>(stats.values());
    }

    public Boolean isPlayed() {
        return played;
    }

    public List<Event> getEvents() {
        return events;
    }

    public long getId() {
        return id;
    }

    public void setPlayed(boolean played) {
        this.played = played;
    }

    //
//    private Event addScore(long id, Player p, int minute) {
//        Event event = new Event(id, p, SCORE, minute);
//
//        if(p.getTeam().equals(home))
//            homeScore++;
//        else
//            awayScore++;
//
//        stats.get(p).addScore();
//        events.add(event);
//
//        return event;
//    }
//
//    private Event addYellowCard(long id, Player p, int minute){
//        Event event = new Event(id, p, YELLOW_CARD, minute);
//
//        stats.get(p).addYellowCard();
//        events.add(event);
//
//        return event;
//    }
//
//    private Event addRedCard(long id, Player p, int minute){
//        Event event = new Event(id, p, RED_CARD, minute);
//
//        stats.get(p).addRedCard();
//        events.add(event);
//
//        return event;
//    }
//
//    private Event addEvent(long id, Player p, EventType type, int minute){
//
//        switch (type) {
//            case RED_CARD:
//                return addRedCard(id, p, minute);
//            case SCORE:
//                return addScore(id, p, minute);
//            case YELLOW_CARD:
//                return addYellowCard(id, p, minute);
//            default:
//                break;
//        }
//
//        return null;
//    }
//
//    public Event addEvent(long id, Player p1, Player p2, EventType type, int minute) {
//
//        if(p2 == null)
//            addEvent(id, p1, type, minute);
//
//        if(type != SUBSTITUTE)
//            return null;
//
//        Event event = new Event(id, p1, p2, SUBSTITUTE, minute);
//        events.add(event);
//
//        return event;
//    }
//
//    public Event changeStats(Player p, EventType type, int amount) {
//
//        switch (type) {
//            case ASSIST:
//                stats.get(p).addAssist(amount);
//                break;
//            case PASS:
//                stats.get(p).addPass(amount);
//                break;
//            case SAVE:
//                stats.get(p).addSave(amount);
//                break;
//            case TACKLE:
//                stats.get(p).addTackle(amount);
//                break;
//            default:
//                break;
//        }
//
//        return null;
//    }
//
//    public void createStats(PlayerStats playerStats) {
//        stats.put(playerStats.getPlayer(), playerStats);
//    }
}
