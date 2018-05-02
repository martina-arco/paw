package ar.edu.itba.model;

/**
 * Created by martina on 30/04/2018.
 */
public class Event {

    private Player p1, p2;
    private EventType type;
    private Integer minute;
    private long id;

    public Event(long id, Player p1, EventType type, Integer minute) {
        this.id = id;
        this.p1 = p1;
        this.p2 = null;
        this.type = type;
        this.minute = minute;
    }

    public Event(long id, Player p1, Player p2, EventType type, Integer minute) {
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
        this.minute = minute;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public EventType getType() {
        return type;
    }

    public Integer getMinute() {
        return minute;
    }

    public long getId() {
        return id;
    }
}
