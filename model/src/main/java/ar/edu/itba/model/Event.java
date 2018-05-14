package ar.edu.itba.model;

public class Event {

    public enum Type {
        RED_CARD,YELLOW_CARD,SCORE,SAVE,PASS,TACKLE,ASSIST,SUBSTITUTE
    }

    private long id, p1Id, p2Id;
    private Type type;
    private Player p1, p2;
    private Integer minute;

    public Event(long id, Player p1, Type type, int minute) {
        this.id = id;
        this.p1 = p1;
        this.type = type;
        this.minute = minute;
    }

    public Event(long id, Player p1, Player p2, Type type, int minute) {
        this.id = id;
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
        this.minute = minute;
    }

    public Event(long id, long p1Id, long p2Id, Type type, Integer minute) {
        this.id = id;
        this.p1Id = p1Id;
        this.p2Id = p2Id;
        this.type = type;
        this.minute = minute;
    }

    public long getP1Id() {
        if(p1 != null)
            return p1.getId();
        return p1Id;
    }

    public long getP2Id() {
        if(p2 != null)
            return p2.getId();
        return p2Id;
    }

    public void setP1(Player p1) {
        this.p1 = p1;
    }

    public void setP2(Player p2) {
        this.p2 = p2;
    }

    public Player getP1() {
        return p1;
    }

    public Player getP2() {
        return p2;
    }

    public Type getType() {
        return type;
    }

    public int getMinute() {
        return minute;
    }

    public long getId() {
        return id;
    }
}
