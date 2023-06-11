package com.example.edf3aly;

import org.junit.Assert;
import org.junit.Test;

import java.util.UUID;

public class AccountTest {

    @Test
    public void testAccountCreation() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        Assert.assertEquals(Account.AccountType.Savings.toString(), acc.getAccType());
        Assert.assertEquals("1001-2345", acc.getAccNo());
        Assert.assertEquals(1000.0, acc.getAccBalance(), 0.0);
    }

    @Test
    public void testSetAccType() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccType(Account.AccountType.Checking.toString());
        Assert.assertEquals(Account.AccountType.Checking.toString(), acc.getAccType());
    }

    @Test
    public void testSetAccNo() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccNo("1002-3456");
        Assert.assertEquals("1002-3456", acc.getAccNo());
    }

    @Test
    public void testSetAccBalanceNormal() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(500.0);
        Assert.assertEquals(500.0, acc.getAccBalance(), 0.0);
    }

    @Test
    public void testSetAccBalanceNegative() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(-500.0);
        Assert.assertNotEquals(0.0, acc.getAccBalance());
    }

    @Test
    public void testSetAccBalanceZero() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(0.0);
        Assert.assertEquals(0.0, acc.getAccBalance(), 0.0);
    }

    @Test
    public void testSetAccBalanceInt() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 2000.0);
        acc.setAccBalance(500);
        Assert.assertNotEquals(2500.0, acc.getAccBalance());
    }

    @Test
    public void testNewBalanceTransfer() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        Account acc2 = new Account(Account.AccountType.Savings, "1002-3456", 1000.0);
        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 500.0, acc2, acc);
        transfer.performTransaction();
        Assert.assertEquals(500.0, acc.getAccBalance(), 0.0);
    }

    @Test
    public void testNewBalanceBuy() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 200.0, "Buy_Item");
        Assert.assertEquals(800.0, acc.getAccBalance(), 0.0);
    }

    @Test
    public void testNewBalancePayBills() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "Pay_Bills");
        Assert.assertEquals(900.0, acc.getAccBalance(), 0.0);
    }

    @Test
    public void testNewBalanceInvalidTransactionType() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "InvalidTransactionType");
        Assert.assertNotEquals(100.0, acc.getAccBalance(), 0.0);
    }
}
