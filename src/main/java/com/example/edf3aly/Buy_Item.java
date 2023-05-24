package com.example.edf3aly;

import java.util.Date;

public class Buy_Item extends Transactions {
    private String store;
    public Buy_Item(int transactionID, double amount, String store)
    {
        super(transactionID, "Buy", amount,new Date());
        this.store = store;
    }
    public String getStore()
    {
        return store;
    }
    public void setStore(String store)
    {
        this.store = store;
    }
}