package com.example.edf3aly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

class TransferTest {

    @BeforeEach
    void setUp() {
        System.out.println("Starting Test Case...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Case Ended...\n------------------");
    }

    // TC1: Successful transfer
    @Test
    public void transferSuccess() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 600.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);
        System.out.println(sourceAccount.getAccBalance());
        System.out.println(targetAccount.getAccBalance());

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        transfer.performTransaction();
        System.out.println("Source Account Balance: " + sourceAccount.getAccBalance());
        System.out.println("Target Account Balance: " + targetAccount.getAccBalance());
        assertEquals(400.0, sourceAccount.getAccBalance());
        assertEquals(200.0, targetAccount.getAccBalance());
    }

    // TC2: Transfer with invalid account number
    @Test
    public void transferWithInvalidAccountNumber() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "12345678", 600.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "123-4567", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.TransferMoney();
        });
    }

    // TC3: Transfer with insufficient funds
    @Test
    public void transferWithInsufficientFunds() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 100.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.TransferMoney();
        });
    }

    // TC4: Transfer with negative amount
    @Test
    public void transferWithNegativeAmount() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 600.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, -200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.TransferMoney();
        });
    }

    // TC5: Concurrent transfers
    @Test
    public void concurrentTransfers() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 1000.0);
        Account targetAccount1 = new Account(Account.AccountType.Savings, "1001-2345", 0.0);
        Account targetAccount2 = new Account(Account.AccountType.Savings, "1001-3456", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        UUID transactionID1 = UUID.randomUUID();
        UUID transactionID2 = UUID.randomUUID();
        Transfer transfer1 = new Transfer(transactionID1, 200.0, targetAccount1, sourceAccount);
        Transfer transfer2 = new Transfer(transactionID2, 300.0, targetAccount2, sourceAccount);
        transfer1.setUser(user);
        transfer2.setUser(user);

        assertAll(
                () -> assertTrue(transfer1.TransferMoney()),
                () -> assertTrue(transfer2.TransferMoney())
        );

        assertEquals(500.0, sourceAccount.getAccBalance());
        assertEquals(200.0, targetAccount1.getAccBalance());
        assertEquals(300.0, targetAccount2.getAccBalance());
    }

    // TC6: Transfer with zero amount
    @Test
    public void transferWithZeroAmount() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 600.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 0.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.TransferMoney();
        });
    }

    // TC7: Transfer from and to the same account
    @Test
    public void transferToSameAccount() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 600.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 200.0, sourceAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.TransferMoney();
        });
    }

    // TC8: Transfer with null source account
    @Test
    public void transferWithNullSourceAccount() {
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", null);

        UUID transactionID = UUID.randomUUID();
        Transfer transfer = new Transfer(transactionID, 200.0, targetAccount, null);
        transfer.setUser(user);

        assertThrows(NullPointerException.class, () -> {
            transfer.TransferMoney();
        });
    }
}
