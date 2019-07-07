package ar.edu.itba.webapp.model.DTOs;

import ar.edu.itba.model.Receipt;

public class ReceiptDTO {

    private Long id;
    private int amount;
    private String type;

    public ReceiptDTO(){}

    public ReceiptDTO(Receipt receipt) {
        this(receipt.getId(), receipt.getAmount(), receipt.getType().toString());
    }

    public ReceiptDTO(Long id, int amount, String type) {
        this.amount = amount;
        this.type = type;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public int getAmount() {
        return amount;
    }

    public String getType() {
        return type;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }
}
