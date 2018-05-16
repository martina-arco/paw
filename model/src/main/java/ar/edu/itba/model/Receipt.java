package ar.edu.itba.model;

public class Receipt {

    public enum Type {
        SOLDPLAYER, BOUGHTPLAYER, EXPANDEDSTADIUM, MATCHINCOME, TOURNAMENTPRIZE, PLAYERSSALARIES
    }

    private int amount;
    private Type type;
    private long id;

    public Receipt(long id, final int amount, final Type type){
        this.amount = amount;
        this.type = type;
        this.id = id;
    }

    public int getAmount(){
        return amount;
    }

    public long getId() {
        return id;
    }

    public Type getType(){
        return type;
    }
}
