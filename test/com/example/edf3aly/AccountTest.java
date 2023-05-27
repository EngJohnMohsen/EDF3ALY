package com.example.edf3aly;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

    // Test case for creating a new Account object
    @Test
    public void testAccountCreation() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        Assertions.assertEquals("Savings", acc.getAccType());
        Assertions.assertEquals("12345678", acc.getAccNo());
        Assertions.assertEquals(1000.0, acc.getAccBalance());
    }

    // Test case for setting the account type
    @Test
    public void testSetAccType() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.setAccType("Checking");
        Assertions.assertEquals("Checking", acc.getAccType());
    }

    // Test case for setting the account number
    @Test
    public void testSetAccNo() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.setAccNo("87654321");
        Assertions.assertEquals("87654321", acc.getAccNo());
    }

    // Test case for setting the account balance normal
    @Test
    public void testSetAccBalance() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.setAccBalance(500.0);
        Assertions.assertEquals(500.0, acc.getAccBalance());
    }

    // Test case for setting the account balance -ve
    @Test
    public void testSetAccBalance1() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.setAccBalance(-500.0);
        Assertions.assertNotEquals(500.0, acc.getAccBalance());
    }

    // Test case for setting the account balance to zeor
    @Test
    public void testSetAccBalance2() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.setAccBalance(-1000.0);
        Assertions.assertNotEquals(0.0, acc.getAccBalance());
    }

    // Test case for setting the account balance with an int expected to fail
    @Test
    public void testSetAccBalance3() {
        Account acc = new Account("Savings", "12345678", 2000);
        //acc.setAccBalance(50);
        Assertions.assertNotEquals(2500, acc.getAccBalance());
    }

    // Test case for updating the account balance with a transfer transaction
    @Test
    public void testNewBalanceTransfer() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.newBalance(acc.getAccBalance(), 500.0, "Transfer");
        Assertions.assertEquals(500.0, acc.getAccBalance());
    }

    // Test case for updating the account balance with a buy transaction
    @Test
    public void testNewBalanceBuy() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.newBalance(acc.getAccBalance(), 200.0, "Buy");
        Assertions.assertEquals(800.0, acc.getAccBalance());
    }

    // Test case for updating the account balance with a pay bills transaction
    @Test
    public void testNewBalancePayBills() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "PayBills");
        Assertions.assertEquals(900.0, acc.getAccBalance());
    }

    // Test case for updating the account balance with an invalid transaction type
    @Test
    public void testNewBalanceInvalidTransactionType() {
        Account acc = new Account("Savings", "12345678", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "InvalidTransactionType");
        Assertions.assertEquals(1000.0, acc.getAccBalance());
}
}