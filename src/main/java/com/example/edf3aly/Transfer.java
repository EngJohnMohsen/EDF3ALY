package com.example.edf3aly;

import java.util.Date;

public class Transfer extends Transactions {

    private String targetAccount;

    public Transfer(int transactionID, double amount, String targetAccount)
    {
        super(transactionID, "Transfer", amount,new Date());
        this.targetAccount = targetAccount;
    }

    public String getTargetAccount()
    {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount)
    {
        this.targetAccount = targetAccount;
    }
}
