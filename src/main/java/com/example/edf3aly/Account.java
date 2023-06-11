package com.example.edf3aly;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Account {
    private String accType;
    private String accNo;
    private double accBalance;
    private List<Transactions> transactions;

    public Account(/*String accType,*/AccountType accountType, String accNo, double accBalance){
        //this.accType = accType;
        this.accNo = accNo;
        this.accBalance = accBalance;
        this.accType = accountType.toString();
        this.transactions = new ArrayList<>();
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getAccNo() {
        if (!accNo.matches("100\\d{1}-\\d{4}")) {
            throw new IllegalArgumentException("Invalid account number");
        }
        return accNo;
    }

    public void setAccNo(String accNo) {
        if (!isValidAccountNumber(accNo)) {
            throw new IllegalArgumentException("Invalid account number");
        }
        this.accNo = accNo;
    }

    public double getAccBalance() {
        return accBalance;
    }

    public void setAccBalance(double accBalance) {
        this.accBalance = accBalance;
    }

    public boolean isValidAccountNumber(String accNo) {
        return accNo.matches("100\\d{1}-\\d{4}");
    }

    public void newBalance(double accBalance, double amount, String transactionType) {
        if (transactionType.equals("Buy_Item") || transactionType.equals("Pay_Bills")) {
            this.accBalance = accBalance - amount;
        } else {
            System.out.println("Insufficient Transaction Type");
        }
    }
    public void addTransaction(Transactions transaction) {
        transactions.add(transaction);
    }
    public List<Transactions> getTransactions() {
        return transactions;
    }

    public enum AccountType { //will change account type to enum
        Savings,
        Checking,
      Credit
}
}