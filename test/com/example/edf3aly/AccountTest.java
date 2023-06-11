package com.example.edf3aly;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class AccountTest {

    @Test
    public void testAccountCreation() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        assertEquals(Account.AccountType.Savings.toString(), acc.getAccType());
        assertEquals("1001-2345", acc.getAccNo());
        assertEquals(1000.0, acc.getAccBalance(),0.01);
    }

    @Test
    public void testSetAccType() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccType(Account.AccountType.Checking.toString());
        assertEquals(Account.AccountType.Checking.toString(), acc.getAccType());
    }

    @Test
    public void testSetAccNo() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccNo("1002-3456");
        assertEquals("1002-3456", acc.getAccNo());
    }

    @Test
    public void testSetAccBalanceNormal() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(500.0);
        assertEquals(500.0, acc.getAccBalance(),0.01);
    }

    @Test
    public void testSetAccBalanceNegative() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(-500.0);
        assertNotEquals(0.0, acc.getAccBalance());
    }

    @Test
    public void testSetAccBalanceZero() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(0.0);
        assertEquals(0.0, acc.getAccBalance(),0.01);
    }

    @Test
    public void testSetAccBalanceInt() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 2000.0);
        acc.setAccBalance(500);
        assertNotEquals(2500.0, acc.getAccBalance());
    }

    @Test
    public void testNewBalanceTransfer() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        Account acc2 = new Account(Account.AccountType.Savings, "1002-3456", 1000.0);
        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 500.0, acc2, acc);
        transfer.performTransaction();
        assertEquals(500.0, acc.getAccBalance(),0.01);
    }

    @Test
    public void testNewBalanceBuy() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 200.0, "Buy_Item");
        assertEquals(800.0, acc.getAccBalance(),0.01);
    }

    @Test
    public void testNewBalancePayBills() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "Pay_Bills");
       assertEquals(900.0, acc.getAccBalance(),0.01);
    }

    @Test
    public void testNewBalanceInvalidTransactionType() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "InvalidTransactionType");
        assertNotEquals(100.0, acc.getAccBalance(),0.01);
    }
}
