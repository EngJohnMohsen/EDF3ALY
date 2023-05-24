package com.example.edf3aly;

public class Transfer extends Transactions {

    private String targetAccount;

    public Transfer(int transactionID, double amount, String targetAccount)
    {
        super(transactionID, "Transfer", amount);
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
