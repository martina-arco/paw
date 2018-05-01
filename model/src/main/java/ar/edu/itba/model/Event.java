package ar.edu.itba.model;

/**
 * Created by martina on 30/04/2018.
 */
public class Event {

    private Player p1, p2;
    private EventType type;
    private Integer minute;

    public Event(Player p1, EventType type, Integer minute) {
        this.p1 = p1;
        this.type = type;
        this.minute = minute;
    }

    public Event(Player p1, Player p2, EventType type, Integer minute) {
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
        this.minute = minute;
    }
}
