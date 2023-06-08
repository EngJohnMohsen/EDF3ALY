package com.example.edf3aly;

import java.util.Date;
import java.util.UUID;



public class Buy_Item extends Transactions {
    private int storeID;
    private int itemID;

    private double itemPrice;

    static double totalAmountPaid = 0.0;
    private String itemName;
    private Account account; // Add account field
    public Buy_Item(UUID transactionID, double amount, String itemName, int storeID, int itemID)
    {
        super(transactionID, "Buy_Item", amount, new Date());
        this.storeID = storeID;
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemPrice = amount;
        // Update the total amount paid
        totalAmountPaid += amount;
    }
    public double getItemPrice() {
        return itemPrice;
    }

    public Account getAccount() {
        return account;
    }

    public static double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }

    public void setUser(User user) {
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void performTransaction() {
        if (account == null) {
            throw new IllegalStateException("No account associated with the transaction.");
        }
        if (account.getAccBalance() >= getItemPrice()) {
            account.newBalance(account.getAccBalance(), amount, getTransactionType());
            System.out.println("Transaction ID: " + getTransactionID());
            System.out.println(" - Item: " + itemName);
            System.out.println(" - Amount: " + itemPrice);
            System.out.println(" Successfully Paid");
        } else {
            System.out.println("Transaction ID: " + getTransactionID());
            System.out.println(" - Item: " + itemName);
            System.out.println(" - Amount: " + itemPrice);
            System.out.println(" Insufficient balance");
            System.out.println(" Transaction Failed");
  }
    }
}