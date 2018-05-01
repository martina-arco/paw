package ar.edu.itba.model;


public class Stadium {

    public enum SeatType {
        LOW,MEDIUM,HIGH
    }
    private static final int lowCost = 50, mediumCost = 100, highCost = 200;
    private long id;
    private String name;
    private int lowClass, lowClassPrice;
    private int mediumClass, mediumClassPrice;
    private int highClass, highClassPrice;

    public Stadium(long id, String name, int lowClass, int lowClassPrice, int mediumClass, int mediumClassPrice, int highClass, int highClassPrice) {
        this.id = id;
        this.name = name;
        this.lowClass = lowClass;
        this.lowClassPrice = lowClassPrice;
        this.mediumClass = mediumClass;
        this.mediumClassPrice = mediumClassPrice;
        this.highClass = highClass;
        this.highClassPrice = highClassPrice;
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
}