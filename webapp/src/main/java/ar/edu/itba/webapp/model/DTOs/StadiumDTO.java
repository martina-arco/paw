package ar.edu.itba.webapp.model.DTOs;


import ar.edu.itba.model.Stadium;

public class StadiumDTO {

    private long id;
    private String name;

    private int lowClass, mediumClass, highClass;
    private int lowClassPrice, mediumClassPrice, highClassPrice;
    private int lowCost, mediumCost, highCost;

    public StadiumDTO(Stadium stadium) {
        this(stadium.getId(), stadium.getName(), stadium.getLowClass(), stadium.getMediumClass(), stadium.getHighClass(),
                stadium.getLowClassPrice(), stadium.getMediumClassPrice(), stadium.getHighClassPrice(),
                Stadium.getLowCost(), Stadium.getMediumCost(), Stadium.getHighCost());
    }

    public StadiumDTO(long id, String name, int lowClass, int mediumClass, int highClass,
                      int lowClassPrice, int mediumClassPrice, int highClassPrice, int lowCost,
                      int mediumCost, int highCost) {
        this.id = id;
        this.name = name;
        this.lowClass = lowClass;
        this.mediumClass = mediumClass;
        this.highClass = highClass;
        this.lowClassPrice = lowClassPrice;
        this.mediumClassPrice = mediumClassPrice;
        this.highClassPrice = highClassPrice;
        this.lowCost = lowCost;
        this.mediumCost = mediumCost;
        this.highCost = highCost;
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

    public void setName(String name) {
        this.name = name;
    }

    public int getLowClass() {
        return lowClass;
    }

    public void setLowClass(int lowClass) {
        this.lowClass = lowClass;
    }

    public int getMediumClass() {
        return mediumClass;
    }

    public void setMediumClass(int mediumClass) {
        this.mediumClass = mediumClass;
    }

    public int getHighClass() {
        return highClass;
    }

    public void setHighClass(int highClass) {
        this.highClass = highClass;
    }

    public int getLowClassPrice() {
        return lowClassPrice;
    }

    public void setLowClassPrice(int lowClassPrice) {
        this.lowClassPrice = lowClassPrice;
    }

    public int getMediumClassPrice() {
        return mediumClassPrice;
    }

    public void setMediumClassPrice(int mediumClassPrice) {
        this.mediumClassPrice = mediumClassPrice;
    }

    public int getHighClassPrice() {
        return highClassPrice;
    }

    public void setHighClassPrice(int highClassPrice) {
        this.highClassPrice = highClassPrice;
    }

    public int getLowCost() {
        return lowCost;
    }

    public int getMediumCost() {
        return mediumCost;
    }

    public int getHighCost() {
        return highCost;
    }
}
