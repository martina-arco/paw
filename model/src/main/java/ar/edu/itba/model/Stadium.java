package ar.edu.itba.model;


public class Stadium {

    public enum SeatType {
        LOW,MEDIUM,HIGH
    }

    private static final int lowCost = 50, mediumCost = 100, highCost = 200;

    public static int getHighCost() {
        return highCost;
    }

    public static int getMediumCost() {
        return mediumCost;
    }

    public static int getLowCost() {
        return lowCost;
    }

    private int lowClass, lowClassPrice;
    private int mediumClass, mediumClassPrice;
    private int highClass, highClassPrice;
    private String name;

    public Stadium(final String name){
        this.name = name;
        lowClass = 0;
        mediumClass = 0;
        highClass = 0;
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


}