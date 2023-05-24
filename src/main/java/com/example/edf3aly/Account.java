package com.example.edf3aly;

public class Account {
    private String accType;
    private String accNo;
    private double accBalance;

    public Account(String accType, String accNo, double accBalance)
    {
        this.accType = accType;
        this.accNo = accNo;
        this.accBalance = accBalance;
    }

    public String getAccType()
    {
        return accType;
    }

    public String getAccNo()
    {
        return accNo;
    }

    public double getAccBalance()
    {
        return accBalance;
    }

    public void setAccType(String accType)
    {
        this.accType = accType;
    }

    public void setAccNo(String accNo)
    {
        this.accNo = accNo;
    }

    public void setAccBalance(double accBalance)
    {
        this.accBalance = accBalance;
    }
    public void newBalance(double accBalance, double amount, String transactionType) {
        if (transactionType.equals("Deposit"))
        {
            this.accBalance = accBalance + amount;
        }
        else if (transactionType.equals("Transfer") || transactionType.equals("Withdraw") ||
                transactionType.equals("Buy") || transactionType.equals("PayBills"))
        {
            this.accBalance = accBalance - amount;
        }
    }
}