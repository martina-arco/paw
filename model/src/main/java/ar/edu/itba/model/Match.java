package ar.edu.itba.model;

import java.util.List;
import java.util.Map;

import static ar.edu.itba.model.EventType.*;

/**
 * Created by martina on 30/04/2018.
 */

public class Match {

    private Team home, away;
    private Integer homeScore, awayScore, homePoints, awayPoints;
    private Map<Player, PlayerStats> stats;
    private Boolean played;
    private List<Event> events;

    public Match(Team home, Team away, Integer homeScore, Integer awayScore, Integer homePoints,
                 Integer awayPoints, Map<Player, PlayerStats> stats, Boolean played, List<Event> events) {
        this.home = home;
        this.away = away;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.homePoints = homePoints;
        this.awayPoints = awayPoints;
        this.stats = stats;
        this.played = played;
        this.events = events;
    }

    private void addScore(Player p, Integer minute) {
        stats.get(p).addScore();
        events.add(new Event(p, SCORE, minute));
    }

    private void addYellowCard(Player p, Integer minute){
        stats.get(p).addYellowCard();
        events.add(new Event(p, YELLOW_CARD, minute));
    }

    private void addRedCard(Player p, Integer minute){
        stats.get(p).addRedCard();
        events.add(new Event(p, RED_CARD, minute));
    }

    public void addEvent(Player p, EventType type, Integer minute){
        switch (type) {
            case RED_CARD:
                addRedCard(p, minute);
                break;
            case SCORE:
                addScore(p, minute);
                break;
            case YELLOW_CARD:
                addYellowCard(p, minute);
                break;
            default:
                break;
        }
    }

    public void addEvent(Player p, EventType type) {
        switch (type) {
            case ASSIST:
                stats.get(p).addAssist();
                break;
            case PASS:
                stats.get(p).addPass();
                break;
            case SAVE:
                stats.get(p).addSave();
                break;
            default:
                break;
        }
    }

    public void addEvent(Player p1, Player p2, EventType type, Integer minute) {
        if(type != SUBSTITUTE)
            return;

        events.add(new Event(p1, p2, SUBSTITUTE, minute));
    }


}
