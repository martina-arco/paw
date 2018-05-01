package ar.edu.itba.model;

public class Receipt {

    public enum ReceiptType {
        SOLDPLAYER, BOUGHTPLAYER, EXPANDEDSTADIUM, MATCHINCOME, TOURNAMENTPRICE
    }

    private Integer amount;
    private ReceiptType type;

    public Receipt(Integer amount, ReceiptType type){
        this.amount = amount;
        this.type = type;
    }

    public Integer getAmount(){
        return amount;
    }

    public ReceiptType getType(){
        return type;
    }
}
