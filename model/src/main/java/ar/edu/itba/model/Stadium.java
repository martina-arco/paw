package ar.edu.itba.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@Table(name = "stadium")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class)
public class Stadium {

    public enum SeatType {
        LOW,MEDIUM,HIGH
    }

    private static final int lowCost = 50, mediumCost = 100, highCost = 200;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stadium_stadiumid_seq")
    @SequenceGenerator(sequenceName = "stadium_stadiumid_seq", name = "stadium_stadiumid_seq", allocationSize = 1)
    @Column(name = "stadiumid")
    private long id;

    @Column(nullable = false)
    private String name;

    @Transient
    private long teamId;

    @OneToOne
    @JoinColumn(name = "team")
    private Team team;

    private int lowClass, mediumClass, highClass;

    private int lowClassPrice, mediumClassPrice, highClassPrice;

    public Stadium(){}

    public Stadium(String name, Team team, int lowClass, int mediumClass, int highClass, int lowClassPrice,
                   int mediumClassPrice, int highClassPrice) {
        this.name = name;
        this.team = team;
        this.lowClass = lowClass;
        this.mediumClass = mediumClass;
        this.highClass = highClass;
        this.lowClassPrice = lowClassPrice;
        this.mediumClassPrice = mediumClassPrice;
        this.highClassPrice = highClassPrice;
    }

    public Stadium(long id, String name, Team team, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice) {
        this.id = id;
        this.name = name;
        this.team = team;
        this.lowClass = lowClass;
        this.lowClassPrice = lowClassPrice;
        this.mediumClass = mediumClass;
        this.mediumClassPrice = mediumClassPrice;
        this.highClass = highClass;
        this.highClassPrice = highClassPrice;
    }

    public Stadium(long id, String name, long teamId, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice) {
        this.id = id;
        this.name = name;
        this.teamId = teamId;
        this.lowClass = lowClass;
        this.lowClassPrice = lowClassPrice;
        this.mediumClass = mediumClass;
        this.mediumClassPrice = mediumClassPrice;
        this.highClass = highClass;
        this.highClassPrice = highClassPrice;
    }

    public long getTeamId() {
        if(team != null)
            return team.getId();
        return teamId;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public static int getHighCost() {
        return highCost;
    }

    public static int getMediumCost() {
        return mediumCost;
    }

    public static int getLowCost() {
        return lowCost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getHighClass() {
        return highClass;
    }

    public int getHighClassPrice() {
        return highClassPrice;
    }

    public int getMediumClass() {
        return mediumClass;
    }

    public int getMediumClassPrice() {
        return mediumClassPrice;
    }

    public int getLowClass() {
        return lowClass;
    }

    public int getLowClassPrice() {
        return lowClassPrice;
    }

    public void addSeats(final SeatType type, final int amount){
        switch(type){
            case HIGH:
                highClass += amount;
                break;
            case MEDIUM:
                mediumClass += amount;
                break;
            case LOW:
                lowClass += amount;
                break;
        }
    }

    public int calculateMatchEarnings() {
        return highClass*highClassPrice + mediumClass*mediumClassPrice + lowClass*lowClassPrice;
    }
}