package com.example.edf3aly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class AccountTest {

    @Test
    public void testAccountCreation() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        Assertions.assertEquals(Account.AccountType.Savings.toString(), acc.getAccType());
        Assertions.assertEquals("1001-2345", acc.getAccNo());
        Assertions.assertEquals(1000.0, acc.getAccBalance());
    }

    @Test
    public void testSetAccType() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccType(Account.AccountType.Checking.toString());
        Assertions.assertEquals(Account.AccountType.Checking.toString(), acc.getAccType());
    }

    @Test
    public void testSetAccNo() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccNo("1002-3456");
        Assertions.assertEquals("1002-3456", acc.getAccNo());
    }

    @Test
    public void testSetAccBalanceNormal() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(500.0);
        Assertions.assertEquals(500.0, acc.getAccBalance());
    }

    @Test
    public void testSetAccBalanceNegative() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(-500.0);
        Assertions.assertNotEquals(0.0, acc.getAccBalance());
    }

    @Test
    public void testSetAccBalanceZero() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.setAccBalance(0.0);
        Assertions.assertEquals(0.0, acc.getAccBalance());
    }

    @Test
    public void testSetAccBalanceInt() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 2000.0);
        acc.setAccBalance(500);
        Assertions.assertNotEquals(2500.0, acc.getAccBalance());
    }

    @Test
    public void testNewBalanceTransfer() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        Account acc2 = new Account(Account.AccountType.Savings, "1002-3456", 1000.0);
        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 500.0, acc2, acc);
        transfer.performTransaction();
        Assertions.assertEquals(500.0, acc.getAccBalance());
    }

    @Test
    public void testNewBalanceBuy() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 200.0, "Buy_Item");
        Assertions.assertEquals(800.0, acc.getAccBalance());
    }

    @Test
    public void testNewBalancePayBills() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "Pay_Bills");
        Assertions.assertEquals(900.0, acc.getAccBalance());
    }

    @Test
    public void testNewBalanceInvalidTransactionType() {
        Account acc = new Account(Account.AccountType.Savings, "1001-2345", 1000.0);
        acc.newBalance(acc.getAccBalance(), 100.0, "InvalidTransactionType");
        Assertions.assertNotEquals(100.0, acc.getAccBalance());
    }
}
