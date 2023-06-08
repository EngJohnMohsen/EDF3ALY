package com.example.edf3aly;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

public class TransactionsTest {

    @Test
    public void testTransactionID() {
        UUID expectedTransactionID = UUID.randomUUID();
        Transactions transactions = new Transactions(expectedTransactionID, "purchase", 100.0, new Date());
        UUID actualTransactionID = transactions.getTransactionID();
        Assertions.assertEquals(expectedTransactionID, actualTransactionID);
    }

    @Test
    public void testTransactionType() {
        String expectedTransactionType = "purchase";
        Transactions transactions = new Transactions(UUID.randomUUID(), expectedTransactionType, 100.0, new Date());
        String actualTransactionType = transactions.getTransactionType();
        Assertions.assertEquals(expectedTransactionType, actualTransactionType);
    }

    @Test
    public void testAmount() {
        double expectedAmount = 100.0;
        Transactions transactions = new Transactions(UUID.randomUUID(), "purchase", expectedAmount, new Date());
        double actualAmount = transactions.getAmount();
        Assertions.assertEquals(expectedAmount, actualAmount, 0.01); // 0.01 is the delta for double comparison
    }

    @Test
    public void testDate() {
        Date expectedDate = new Date();
        Transactions transactions = new Transactions(UUID.randomUUID(), "purchase", 100.0, expectedDate);
        Date actualDate = transactions.getDate();
        Assertions.assertEquals(expectedDate, actualDate);
    }

    @Test
    public void testSuccessfulTransaction() {
        double expectedAmount = 100.0;
        Transactions transactions = new Transactions(UUID.randomUUID(), "purchase", expectedAmount, new Date());
        boolean isSuccessful = transactions.processTransaction();
        Assertions.assertTrue(isSuccessful);
    }

    @Test
    public void testInvalidTransaction() {
        double expectedAmount = -50.0;
        Transactions transactions = new Transactions(UUID.randomUUID(), "purchase", expectedAmount, new Date());
        boolean isSuccessful = transactions.processTransaction();
        Assertions.assertFalse(isSuccessful);
    }
}
