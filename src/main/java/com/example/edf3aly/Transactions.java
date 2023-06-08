package com.example.edf3aly;

import java.util.Date;
import java.util.UUID;

public class Transactions {
    private UUID transactionID;
    private String transactionType;
    public double amount;
    private Date date;

    public Transactions(UUID transactionID, String transactionType, double amount, Date date) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = date;
    }

    public UUID getTransactionID() {
        return transactionID;
    }

    public void setTransactionID(UUID transactionID) {
        this.transactionID = transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean processTransaction() {
        if (amount > 0) {
            return true;
        } else {
            return false;
        }
    }
}
