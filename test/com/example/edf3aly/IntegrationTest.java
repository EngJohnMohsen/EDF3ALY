package com.example.edf3aly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class IntegrationTest {

    private User user;
    private Account account;

    @BeforeEach
    public void setup() {
        // Initialize user and account objects for testing
        account = new Account(Account.AccountType.Checking, "1001-1234", 1000);
        user = new User("John Doe", "123456789", "1234567890", "johndoe", "password", account);
    }



    @Test
    public void testPayBillsTransaction_Successful() {
        double initialBalance = account.getAccBalance();
        double billAmount = 200.0;
        UUID transactionId = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionId, billAmount,  "Electricity Bill", true);
        payBills.setAccount(account);
        int paymentDay = 11;

        // Set the modified payment day
        payBills.setPaymentDay(paymentDay);

        payBills.performTransaction();

        // Check if the bill amount is deducted from the account balance
        Assertions.assertEquals(initialBalance - billAmount, account.getAccBalance());
        // Check if the transaction is added to the account's transaction history
        Assertions.assertTrue(account.getTransactions().contains(payBills));
    }

    @Test
    public void testPayBillsTransaction_InsufficientBalance() {
        double initialBalance = account.getAccBalance();
        double billAmount = initialBalance + 100.0; // Set bill amount greater than account balance
        UUID transactionId = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionId, billAmount, "Electricity Bill", true);
        payBills.setAccount(account);
        int paymentDay = 11;
        // Set the modified payment day
        payBills.setPaymentDay(paymentDay);

        Assertions.assertThrows(IllegalStateException.class, payBills::performTransaction);
        // Check if the account balance remains unchanged
        Assertions.assertEquals(initialBalance, account.getAccBalance());
        // Check if the transaction is not added to the account's transaction history
        Assertions.assertFalse(account.getTransactions().contains(payBills));
    }

    @Test
    public void testTransferTransaction_Successful() {
        double initialSourceBalance = account.getAccBalance();
        double initialTargetBalance = 500.0;
        double transferAmount = 200.0;
        UUID transactionId = UUID.randomUUID();
        Account sourceAccount = new Account(Account.AccountType.Savings, "1002-5678", initialSourceBalance);
        Account targetAccount = new Account(Account.AccountType.Checking, "1003-9012", initialTargetBalance);
        Transfer transfer = new Transfer(transactionId, transferAmount, targetAccount, sourceAccount);
        transfer.setUser(user);

        transfer.TransferMoney();

        // Check if the transfer amount is deducted from the source account balance
        Assertions.assertEquals(initialSourceBalance - transferAmount, sourceAccount.getAccBalance());
// Check if the transfer amount is added to the target account balance
        Assertions.assertEquals(initialTargetBalance + transferAmount, targetAccount.getAccBalance());
// Check if the transaction is added to both the source and target account's transaction history
        Assertions.assertTrue(sourceAccount.getTransactions().contains(transfer));
       Assertions.assertTrue(targetAccount.getTransactions().contains(transfer));
    }

    @Test
    public void testTransferTransaction_InsufficientBalance() {
        double initialSourceBalance = account.getAccBalance();
        double initialTargetBalance = 500.0;
        double transferAmount = initialSourceBalance + 100.0; // Set transfer amount greater than source account balance
        UUID transactionId = UUID.randomUUID();
        Account sourceAccount = new Account(Account.AccountType.Savings, "1002-5678", initialSourceBalance);
        Account targetAccount = new Account(Account.AccountType.Checking, "1003-9012", initialTargetBalance);
        Transfer transfer = new Transfer(transactionId, transferAmount, targetAccount, sourceAccount);
        transfer.setUser(user);

        Assertions.assertThrows(IllegalArgumentException.class, transfer::TransferMoney);
        // Check if the source and target account balances remain unchanged
        Assertions.assertEquals(initialSourceBalance, sourceAccount.getAccBalance());
        Assertions.assertEquals(initialTargetBalance, targetAccount.getAccBalance());
        // Check if the transaction is not added to the source and target account's transaction history
        Assertions.assertFalse(sourceAccount.getTransactions().contains(transfer));
        Assertions.assertFalse(targetAccount.getTransactions().contains(transfer));
    }
    @Test
    public void testBuyItemTransaction_Successful() {
        double initialBalance = account.getAccBalance();
        double itemPrice = 50.0;
        UUID transactionId = UUID.randomUUID();
        int storeId = 1;
        int itemId = 100;
        String itemName = "Item A";
        Buy_Item buyItem = new Buy_Item(transactionId, itemPrice, itemName, storeId, itemId);
        buyItem.setAccount(account);

        buyItem.performTransaction();

        // Check if the item price is deducted from the account balance
        Assertions.assertEquals(initialBalance - itemPrice, account.getAccBalance());
        // Check if the transaction is added to the account's transaction history
        Assertions.assertTrue(account.getTransactions().contains(buyItem));
    }

    @Test
    public void testBuyItemTransaction_NullAccount() {
        // Set up initial account balance
        double initialBalance = 500.0;
        account.setAccBalance(initialBalance);

        // Set up buy item transaction
        double itemPrice = initialBalance + 100.0; // Set item price greater than account balance
        UUID transactionId = UUID.randomUUID();
        int storeId = 1;
        int itemId = 100;
        String itemName = "Item B";
        Buy_Item buyItem = new Buy_Item(transactionId, itemPrice, itemName, storeId, itemId);
        // Do not set the account for the buy item transaction

        // Perform the transaction and assert the expected behavior
        Assertions.assertThrows(IllegalStateException.class, buyItem::performTransaction);
        Assertions.assertEquals(initialBalance, account.getAccBalance());
        Assertions.assertFalse(account.getTransactions().contains(buyItem));
    }
    @Test
    public void testBuyItemTransaction_InsufficientBalance() {
        // Set up initial account balance
        double initialBalance = 500.0;
        account.setAccBalance(initialBalance);

        // Set up buy item transaction
        double itemPrice = 700.0; // Set item price greater than account balance
        UUID transactionId = UUID.randomUUID();
        int storeId = 1;
        int itemId = 100;
        String itemName = "Item B";
        Buy_Item buyItem = new Buy_Item(transactionId, itemPrice, itemName, storeId, itemId);
        buyItem.setAccount(account);

        // Perform the transaction and assert the expected behavior
        Assertions.assertEquals(initialBalance, account.getAccBalance());
        Assertions.assertFalse(account.getTransactions().contains(buyItem));
    }


}