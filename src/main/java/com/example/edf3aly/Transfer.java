package com.example.edf3aly;

import java.nio.channels.Pipe;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class Transfer extends Transactions {

    private Account targetAccount;
    private Account sourceAccount;
    private User user;



    public Transfer(UUID transactionID, double amount, Account targetAccount, Account sourceAccount){
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

    public boolean TransferMoney() {
        // Check if the transfer amount is valid
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid transfer amount");
        }

        // Check if the target account is null
        if (targetAccount == null) {
            throw new IllegalArgumentException("Target account not set");
        }

        // Check if the target account does not match the required pattern
        if (!targetAccount.isValidAccountNumber(targetAccount.getAccNo())) {
            throw new IllegalArgumentException("Invalid target account");
        }

        // Check if the source account does not match the required pattern
        if (!sourceAccount.isValidAccountNumber(sourceAccount.getAccNo())) {
            throw new IllegalArgumentException("Invalid source account");
        }

        // Check if the source account is null
        if (sourceAccount == null) {
            throw new IllegalArgumentException("Source account not set");
        }

        // Check if the user's balance is sufficient for the transfer
        if (amount > sourceAccount.getAccBalance()) {
            throw new IllegalArgumentException("Insufficient balance for transfer");
        }

        // Check if the source account and target account are the same
        if (sourceAccount.equals(targetAccount)) {
            throw new IllegalArgumentException("Source and target accounts cannot be the same");
        }


        // Subtract the amount from the source account's balance
        double newSourceBalance = sourceAccount.getAccBalance() - amount;
         sourceAccount.setAccBalance(newSourceBalance);
        sourceAccount.addTransaction(this);


        // Add the amount to the target account's balance
        double newTargetBalance = targetAccount.getAccBalance() + amount;
        targetAccount.setAccBalance(newTargetBalance);
        targetAccount.addTransaction(this);


        return true ;
    }

    @Override
    public void performTransaction() {
        TransferMoney();
    }



    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean sourceAccount() {
            return true;
    }

    public boolean targetAccount() {
        return true;
    }
}
