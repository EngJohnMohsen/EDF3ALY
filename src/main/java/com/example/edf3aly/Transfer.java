package com.example.edf3aly;
import java.util.Date;
import java.util.InputMismatchException;

public class Transfer extends Transactions {

    private String targetAccount;
    private String sourceAccount;

    private User user;


    public Transfer(int transactionID, double amount, String targetAccount) {
        super(transactionID, "Transfer", amount, new Date());
        this.targetAccount = targetAccount;
    }

    public String getTargetAccount() {
        return targetAccount;
    }

    public void setTargetAccount(String targetAccount) {
        this.targetAccount = targetAccount;
    }

    public String getSourceAccount() {
        return sourceAccount;
    }

    public void setSourceAccount(String sourceAccount) {
        this.sourceAccount = sourceAccount;
    }
    public boolean transferMoney() {
        // Check if the transfer amount is valid
        if (amount <= 0) {
            throw new InputMismatchException("Invalid transfer amount");
        }

        // Check if the target account is empty or contains numbers
        if (targetAccount.isEmpty() || targetAccount.matches(".*[0-9].*")) {
            throw new InputMismatchException("Invalid target account");
        }

        // Check if the user's balance is sufficient for the transfer
        if (amount > user.getBalance()) {
            throw new InputMismatchException("Insufficient balance for transfer");
        }

        // Subtract the amount from the user's balance
        user.setBalance(user.getBalance() - amount);

        // Placeholder logic to add the amount to the transfer account
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
