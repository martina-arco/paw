package ar.edu.itba.model;

import javax.persistence.*;

@Entity
@Table(name = "event")
public class Event {

    public enum Type {
        RED_CARD,YELLOW_CARD,HOMESCORE,AWAYSCORE,SAVE,PASS,TACKLE,ASSIST,SUBSTITUTE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "event_eventid_seq")
    @SequenceGenerator(sequenceName = "event_eventid_seq", name = "event_eventid_seq", allocationSize = 1)
    @Column(name = "eventid")
    private long id;

    @ManyToOne
    @JoinColumn(name = "match", nullable = false)
    private Match match;

    @Transient
    private long p1Id, p2Id;

    @ManyToOne
    @JoinColumn(name = "player1")
    private Player p1;

    @ManyToOne
    @JoinColumn(name = "player2")
    private Player p2;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Type type;

    @Column(nullable = false)
    private int minute;

    public Event(){}

    public Event(Match match, Player p1, Player p2, Type type, int minute) {
        this.match = match;
        this.p1 = p1;
        this.p2 = p2;
        this.type = type;
        this.minute = minute;
    }

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

    public Event(long id, long p1Id, long p2Id, Type type, int minute) {
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

    public Match getMatch() {
        return match;
    }
}
