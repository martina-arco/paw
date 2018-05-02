package ar.edu.itba.model;

public class Receipt {

    public enum ReceiptType {
        SOLDPLAYER, BOUGHTPLAYER, EXPANDEDSTADIUM, MATCHINCOME, TOURNAMENTPRICE
    }

    private int amount;
    private final ReceiptType type;
    private long id;

    public Receipt(long id, final int amount, final ReceiptType type){
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

    public ReceiptType getType(){
        return type;
    }
}
