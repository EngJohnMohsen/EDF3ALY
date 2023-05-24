package com.example.edf3aly;

import java.util.Date;

public class Transactions {
    private int transactionID;
    private String transactionType;
    private double amount;
    private Date date;

    public Transactions(int transactionID, String transactionType, double amount) {
        this.transactionID = transactionID;
        this.transactionType = transactionType;
        this.amount = amount;
        this.date = new Date();
    }

    public int getTransactionID() {
        return transactionID;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public double getAmount() {
        return amount;
    }
    public Date getDate() {
        return date;
    }

    public void setTransactionID(int transactionID) {
        this.transactionID = transactionID;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}