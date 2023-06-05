package com.example.edf3aly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.InputMismatchException;

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
    public void transfer1() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 600.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        Transfer transfer = new Transfer(1, 200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertTrue(transfer.transferMoney());
        assertEquals(400.0, sourceAccount.getAccBalance());
        assertEquals(200.0, targetAccount.getAccBalance());
    }

    // TC2: Transfer with invalid account number
    @Test
    public void transferWithInvalidAccountNumber() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "12345678", 600.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "123-4567", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        Transfer transfer = new Transfer(1, 200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.transferMoney();
        });
    }

    // TC3: Transfer with insufficient funds
    @Test
    public void transferWithInsufficientFunds() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 100.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        Transfer transfer = new Transfer(1, 200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.transferMoney();
        });
    }

    // TC4: Transfer with negative amount
    @Test
    public void transferWithNegativeAmount() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 600.0);
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        Transfer transfer = new Transfer(1, -200.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.transferMoney();
        });
    }

    // TC5: Concurrent transfers
    @Test
    public void concurrentTransfers() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 1000.0);
        Account targetAccount1 = new Account(Account.AccountType.Savings, "1001-2345", 0.0);
        Account targetAccount2 = new Account(Account.AccountType.Savings, "1001-3456", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        Transfer transfer1 = new Transfer(1, 200.0, targetAccount1, sourceAccount);
        Transfer transfer2 = new Transfer(2, 300.0, targetAccount2, sourceAccount);
        transfer1.setUser(user);
        transfer2.setUser(user);

        assertAll(
                () -> assertTrue(transfer1.transferMoney()),
                () -> assertTrue(transfer2.transferMoney())
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

        Transfer transfer = new Transfer(1, 0.0, targetAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.transferMoney();
        });
    }

    // TC7: Transfer from and to the same account
    @Test
    public void transferToSameAccount() {
        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 600.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        Transfer transfer = new Transfer(1, 200.0, sourceAccount, sourceAccount);
        transfer.setUser(user);

        assertThrows(IllegalArgumentException.class, () -> {
            transfer.transferMoney();
        });
    }

    // TC8: Transfer with null source account
    @Test
    public void transferWithNullSourceAccount() {
        Account targetAccount = new Account(Account.AccountType.Savings, "1001-2345", 0.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", null);

        Transfer transfer = new Transfer(1, 200.0, targetAccount, null);
        transfer.setUser(user);

        assertThrows(NullPointerException.class, () -> {
            transfer.transferMoney();
        });
    }

}
