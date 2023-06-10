package com.example.edf3aly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        UserAuthenticationTest.class,
        AccountCreationTest.class,
        TransactionTest.class,
        PayBillsTest.class,
        TransferTest.class,
        BuyItemTest.class,
        NotificationTest.class,
        StatementGeneratorTest.class
})
public class BigBangTestSuite {

    @Test
    public void testFullSystem() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Checking, "1001-1234", 2000);

        // Create multiple instances of Buy_Item
        UUID expectedTransactionID1 = UUID.randomUUID();
        Buy_Item buyItem1 = new Buy_Item(expectedTransactionID1, 200.0, "Phone", 1, 1234);

        UUID expectedTransactionID2 = UUID.randomUUID();
        Buy_Item buyItem2 = new Buy_Item(expectedTransactionID2, 400.0, "Macbook", 1, 16234);

        // Perform the transactions
        buyItem1.setAccount(account);
        buyItem2.setAccount(account);

        buyItem1.performTransaction();
        buyItem2.performTransaction();

        // Get the total amount of bills paid
        double totalAmountPaid = Buy_Item.getTotalAmountPaid();
        double updatedBalance = account.getAccBalance();

        // Verify the total amount matches the sum of item prices
        Assertions.assertEquals(600,totalAmountPaid);

        Assertions.assertEquals(1400,updatedBalance);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);
        Date date = new Date();
        Notifications notification = new Notifications(1, "Transaction successful", date, user);
        String message = notification.getMessage();

        Assertions.assertEquals("Transaction successful",message);


        // Create an instance of Account
        account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);

        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 200.0, 1, "Electricity", true);

        // Modify the payment day
        int paymentDay = 9;

        // Set the modified payment day
        payBills.setPaymentDay(paymentDay);
        // Set the account for the payment
        payBills.setAccount(account);

        // Perform the payment
        payBills.performTransaction();

        // Get the updated account balance
        updatedBalance = account.getAccBalance();

        // Assert the expected account balance
        Assertions.assertEquals(300.0,updatedBalance, 0.0);

        date = new Date();
        notification = new Notifications(1, "Transaction successful", date, user);
        message = notification.getMessage();

        Assertions.assertEquals("Transaction successful",message);

        Account sourceAccount = new Account(Account.AccountType.Savings, "1001-1234", 1000.0);
        Account targetAccount1 = new Account(Account.AccountType.Savings, "1001-2345", 0.0);
        Account targetAccount2 = new Account(Account.AccountType.Savings, "1001-3456", 0.0);

        UUID transactionID1 = UUID.randomUUID();
        UUID transactionID2 = UUID.randomUUID();
        Transfer transfer1 = new Transfer(transactionID1, 200.0, targetAccount1, sourceAccount);
        Transfer transfer2 = new Transfer(transactionID2, 300.0, targetAccount2, sourceAccount);
        transfer1.setUser(user);
        transfer2.setUser(user);

        Assertions.assertAll(
                () -> Assertions.assertTrue(transfer1.transferMoney()),
                () -> Assertions.assertTrue(transfer2.transferMoney())
        );

        Assertions.assertEquals(500.0,sourceAccount.getAccBalance());

        Assertions.assertEquals(200.0,targetAccount1.getAccBalance());

        Assertions.assertEquals(300.0,targetAccount2.getAccBalance());

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StatementGenerator statementGenerator = new StatementGenerator();

        // Creating sample transactions
        UUID expectedTransactionID3 = UUID.randomUUID();
        UUID expectedTransactionID4 = UUID.randomUUID();
        UUID expectedTransactionID5 = UUID.randomUUID();

        Transactions transaction3 = new Transactions(expectedTransactionID3, "Deposit", 1000.0, newDate());
        Transactions transaction4 = new Transactions(expectedTransactionID4, "Withdrawal", 500.0, new Date());
        Transactions transaction5 = new Transactions(expectedTransactionID5, "Transfer", 200.0, new Date());

        // Adding transactions to the account
        account.addTransaction(transaction3);
        account.addTransaction(transaction4);
        account.addTransaction(transaction5);

        // Generating the statement
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
        statementGenerator.generateStatement(account);
        String statement = outputStream.toString();

        // Asserting the statement contains the expected transaction IDs
        Assertions.assertTrue(statement.contains(expectedTransactionID3.toString()));
        Assertions.assertTrue(statement.contains(expectedTransactionID4.toString()));
        Assertions.assertTrue(statement.contains(expectedTransactionID5.toString()));
    }
}