package com.example.edf3aly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
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
        // Set up test data
        double initialBalance = account.getAccBalance();
        double billAmount = 200.0;
        UUID transactionId = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionId, billAmount,  "Electricity Bill", true);
        payBills.setAccount(account);
        int paymentDay = 11;
        payBills.setPaymentDay(paymentDay);

        // Perform the transaction
        payBills.performTransaction();

        // Assertions
        Assertions.assertEquals(initialBalance - billAmount, account.getAccBalance());
        Assertions.assertTrue(account.getTransactions().contains(payBills));
    }

    @Test
    public void testPayBillsTransaction_InsufficientBalance() {
        // Set up test data
        double initialBalance = account.getAccBalance();
        double billAmount = initialBalance + 100.0; // Set bill amount greater than account balance
        UUID transactionId = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionId, billAmount, "Electricity Bill", true);
        payBills.setAccount(account);
        int paymentDay = 11;
        payBills.setPaymentDay(paymentDay);

        // Assertions
        Assertions.assertThrows(IllegalStateException.class, payBills::performTransaction);
        Assertions.assertEquals(initialBalance, account.getAccBalance());
        Assertions.assertFalse(account.getTransactions().contains(payBills));
    }

    @Test
    public void testTransferTransaction_Successful() {
        // Set up test data
        double initialSourceBalance = account.getAccBalance();
        double initialTargetBalance = 500.0;
        double transferAmount = 200.0;
        UUID transactionId = UUID.randomUUID();
        Account sourceAccount = new Account(Account.AccountType.Savings, "1002-5678", initialSourceBalance);
        Account targetAccount = new Account(Account.AccountType.Checking, "1003-9012", initialTargetBalance);
        Transfer transfer = new Transfer(transactionId, transferAmount, targetAccount, sourceAccount);
        transfer.setUser(user);

        // Perform the transaction
        transfer.TransferMoney();

        // Assertions
        Assertions.assertEquals(initialSourceBalance - transferAmount, sourceAccount.getAccBalance());
        Assertions.assertEquals(initialTargetBalance + transferAmount, targetAccount.getAccBalance());
        Assertions.assertTrue(sourceAccount.getTransactions().contains(transfer));
        Assertions.assertTrue(targetAccount.getTransactions().contains(transfer));
    }

    // Additional test methods for other transactions...

    // Sandwich integration testing methods

    @Test
    public void testIntegration_PayBillsAndTransfer_Successful() {
        // Set up test data
        Account targetaccount = new Account(Account.AccountType.Savings, "1002-5678", 500.0);
        double initialBalance = account.getAccBalance();
        double billAmount = 200.0;
        double transferAmount = 100.0;
        UUID transactionId1 = UUID.randomUUID();
        UUID transactionId2 = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionId1, billAmount, "Electricity Bill", true);
        Transfer transfer = new Transfer(transactionId2, transferAmount, targetaccount, account);
        payBills.setAccount(account);
        transfer.setUser(user);
        payBills.setPaymentDay(11);

        // Perform the transactions
        payBills.performTransaction();
        transfer.TransferMoney();

        // Assertions for pay bills transaction
        Assertions.assertEquals(initialBalance - billAmount -transferAmount, account.getAccBalance());
        Assertions.assertTrue(account.getTransactions().contains(payBills));

        // Assertions for transfer transaction
        Assertions.assertEquals(initialBalance - billAmount - transferAmount, account.getAccBalance());
        Assertions.assertTrue(account.getTransactions().contains(transfer));
    }

    @Test
    public void testIntegration_PayBillsAndTransfer_InsufficientBalance() {
        // Set up test data
        double initialBalance = account.getAccBalance();
        double billAmount = initialBalance + 100.0; // Set bill amount greater than account balance
        double transferAmount = 100.0;
        UUID transactionId1 = UUID.randomUUID();
        UUID transactionId2 = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionId1, billAmount, "Electricity Bill", true);
        Transfer transfer = new Transfer(transactionId2, transferAmount, account, account);
        payBills.setAccount(account);
        transfer.setUser(user);

        // Assertions
        Assertions.assertThrows(IllegalStateException.class, () -> {
            // Perform the transactions
            payBills.performTransaction();
            transfer.TransferMoney();
        });
        Assertions.assertEquals(initialBalance, account.getAccBalance());
        Assertions.assertFalse(account.getTransactions().contains(payBills));
        Assertions.assertFalse(account.getTransactions().contains(transfer));
    }
}
