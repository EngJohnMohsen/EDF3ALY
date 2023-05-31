package com.example.edf3aly;

import java.util.Date;
import java.util.InputMismatchException;

public class Transfer extends Transactions {

    private Account targetAccount;
    private Account sourceAccount;

    private User user;

    public Transfer(int transactionID, double amount, Account targetAccount, Account sourceAccount) {
        super(transactionID, "Transfer", amount, new Date());
        this.targetAccount = targetAccount;
        this.sourceAccount = sourceAccount;
    }

    public Account getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(Account targetAccount) {
        this.targetAccount = targetAccount;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(Account sourceAccount) {
        this.sourceAccount = sourceAccount;
    }

    public boolean transferMoney() {
        // Check if the transfer amount is valid
        if (amount <= 0) {
            throw new InputMismatchException("Invalid transfer amount");
        }

        // Check if the target account is null
        if (targetAccount == null) {
            throw new InputMismatchException("Target account not set");
        }

        // Check if the target account does not match the required pattern
        if (!targetAccount.getAccNo().matches("100\\d{1}-\\d{4}")) {
            throw new InputMismatchException("Invalid target account");
        }

        // Check if the source account is null
        if (sourceAccount == null) {
            throw new InputMismatchException("Source account not set");
        }
        // Check if the source account does not match the required pattern
        if (!sourceAccount.getAccNo().matches("100\\d{1}-\\d{4}")) {
            throw new InputMismatchException("Invalid source account");
        }

        // Check if the user's balance is sufficient for the transfer
        if (amount > sourceAccount.getAccBalance()) {
            throw new InputMismatchException("Insufficient balance for transfer");
        }

        // Subtract the amount from the source account's balance
        double newSourceBalance = sourceAccount.getAccBalance() - amount;
        sourceAccount.setAccBalance(newSourceBalance);

        // Add the amount to the target account's balance
        double newTargetBalance = targetAccount.getAccBalance() + amount;
        targetAccount.setAccBalance(newTargetBalance);

        // Placeholder logic to complete the transfer
        // Add your transfer logic here

        return true;
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
