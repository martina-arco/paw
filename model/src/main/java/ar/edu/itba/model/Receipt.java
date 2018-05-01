package ar.edu.itba.model;

public class Receipt {

    public enum ReceiptType {
        SOLDPLAYER, BOUGHTPLAYER, EXPANDEDSTADIUM, MATCHINCOME, TOURNAMENTPRICE
    }

    private int amount;
    private final ReceiptType type;

    public Receipt(final int amount, final ReceiptType type){
        this.amount = amount;
        this.type = type;
    }

    public int getAmount(){
        return amount;
    }

    public ReceiptType getType(){
        return type;
    }
}
