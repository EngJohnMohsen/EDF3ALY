package com.example.edf3aly;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.UUID;

public class Buy_ItemTest {

    @Before
    public void setUp() {
        System.out.println("Starting Test Case...");
    }

    @After
    public void tearDown() {
        System.out.println("Test Case Ended...\n------------------");
    }

    @Test
    public void testPerformTransaction_Successful() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Checking, "1001-1234", 50000.0);

        // Create an instance of Buy_Item
        UUID expectedTransactionID = UUID.randomUUID();
        Buy_Item buyItem = new Buy_Item(expectedTransactionID, 15000.0, "Phone", 1, 1234);

        // Set the account for the transaction
        buyItem.setAccount(account);

        // Perform the transaction
        buyItem.performTransaction();

        // Get the updated account balance
        double updatedBalance = account.getAccBalance();

        // Assert the expected account balance
        assertEquals(35000, updatedBalance, 0.0);
    }

    @Test
    public void testPerformTransaction_InsufficientBalance() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Checking, "1001-1234", 10000);

        // Create an instance of Buy_Item
        UUID expectedTransactionID = UUID.randomUUID();
        Buy_Item buyItem = new Buy_Item(expectedTransactionID, 15000, "Laptop", 1333, 1412);

        // Set the account for the transaction
        buyItem.setAccount(account);

        try {
            // Perform the payment for bill1
            buyItem.performTransaction();
        } catch (IllegalArgumentException e) {
            // Handle the exception (payment day not reached)
            System.out.println("Payment day not reached. Ignoring the payment for bill1.");
        }

        // Get the updated account balance
        double updatedBalance = account.getAccBalance();

        // Assert that the account balance remains unchanged
        assertNotEquals(-5000, updatedBalance, 0.0);
    }

    @Test
    public void testPerformTransaction_AccountNotSet() {
        // Create an instance of Buy_Item
        UUID expectedTransactionID = UUID.randomUUID();
        Buy_Item buyItem = new Buy_Item(expectedTransactionID, 200.0, "Phone", 1, 1234);

        // Perform the transaction without setting the account

        try {
            buyItem.performTransaction();
            fail("Expected IllegalStateException was not thrown.");
        } catch (IllegalStateException e) {
            // Exception expected, so test is successful
        }
    }

    @Test
    public void testPerformTransaction_Successful1() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Checking, "1001-1234", 1000.0);

        // Create an instance of Buy_Item
        UUID expectedTransactionID = UUID.randomUUID();
        Buy_Item buyItem = new Buy_Item(expectedTransactionID, 200.0, "Phone", 1, 1234);
        buyItem.setAccount(account);

        // Perform the transaction
        buyItem.performTransaction();

        // Verify the account balance is updated correctly
        double updatedBalance = account.getAccBalance();
        assertEquals(800, updatedBalance, 0.0);
    }

    @Test
    public void testGetStoreID() {
        // Create an instance of Buy_Item
        UUID expectedTransactionID = UUID.randomUUID();
        Buy_Item buyItem = new Buy_Item(expectedTransactionID, 200.0, "Phone", 1, 1234);
        buyItem.setStoreID(456);

        // Verify the retrieved store ID matches the set value
        int storeID = buyItem.getStoreID();
        assertEquals(456, storeID);
    }

    @Test
    public void testGetItemID() {
        // Create an instance of Buy_Item
        UUID expectedTransactionID = UUID.randomUUID();
        Buy_Item buyItem = new Buy_Item(expectedTransactionID, 200.0, "Phone", 1, 1234);
        buyItem.setItemID(789);

        // Verify the retrieved item ID matches the set value
        int itemID = buyItem.getItemID();
        assertEquals(789, itemID);
    }

    @Test
    public void testGetItemPrice() {
        // Create an instance of Buy_Item
        UUID expectedTransactionID = UUID.randomUUID();
        Buy_Item buyItem = new Buy_Item(expectedTransactionID, 200.0, "Phone", 1, 1234);

        // Verify the retrieved item price matches the set value
        double itemPrice = buyItem.getItemPrice();
        assertEquals(200, itemPrice, 0.0);
    }

    @Test
    public void testCalculateTotalAmountOfBillsPaid1() {
        Buy_Item.totalAmountPaid = 0.0;
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Checking, "1001-1234", 50000);
        // Create multiple instances of Buy_Item
        UUID expectedTransactionID1 = UUID.randomUUID();
        Buy_Item buyItem1 = new Buy_Item(expectedTransactionID1, 30000, "Phone", 1, 1234);
        buyItem1.setAccount(account);
        buyItem1.performTransaction();

        UUID expectedTransactionID2 = UUID.randomUUID();
        Buy_Item buyItem2 = new Buy_Item(expectedTransactionID2, 7600, "Airpods", 1, 12734);
        // Perform the transactions
        buyItem2.setAccount(account);
        buyItem2.performTransaction();

        // Get the total amount of bills paid
        double totalAmount = Buy_Item.getTotalAmountPaid();
        double updatedBalance = account.getAccBalance();
        // Verify the total amount matches the sum of item prices
        assertEquals(37600, totalAmount, 0.0);
        assertEquals(12400, updatedBalance, 0.0);
    }

    @Test
    public void testCalculateTotalAmountOfBillsPaid() {
        Buy_Item.totalAmountPaid = 0.0;
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

        // Perform the transactions
        buyItem1.performTransaction();
        buyItem2.performTransaction();

        // Get the total amount of bills paid
        double totalAmountPaid = Buy_Item.getTotalAmountPaid();
        double updatedBalance = account.getAccBalance();

        // Verify the total amount matches the sum of item prices
        assertEquals(600, totalAmountPaid, 0.0);
        assertEquals(1400, updatedBalance, 0.0);
    }

}
