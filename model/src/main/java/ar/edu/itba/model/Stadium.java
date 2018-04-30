package ar.edu.itba.model;

import static ar.edu.itba.model.SeatType.*;

public class Stadium {

    private static final Integer lowCost = 50, mediumCost = 100, highCost = 200;

    public static Integer getHighCost() {
        return highCost;
    }

    public static Integer getMediumCost() {
        return mediumCost;
    }

    public static Integer getLowCost() {
        return lowCost;
    }

    private Integer lowClass, lowClassPrice;
    private Integer mediumClass, mediumClassPrice;
    private Integer highClass, highClassPrice;
    private String name;

    public Stadium(String name){
        this.name = name;
        lowClass = 0;
        mediumClass = 0;
        highClass = 0;
    }

    public String getName() {
        return name;
    }

    public Integer getHighClass() {
        return highClass;
    }

    public Integer getHighClassPrice() {
        return highClassPrice;
    }

    public Integer getMediumClass() {
        return mediumClass;
    }

    public Integer getMediumClassPrice() {
        return mediumClassPrice;
    }

    public Integer getLowClass() {
        return lowClass;
    }

    public Integer getLowClassPrice() {
        return lowClassPrice;
    }

    public void addSeats(SeatType type, Integer amount){
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


}