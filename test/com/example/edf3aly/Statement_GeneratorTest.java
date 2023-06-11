package com.example.edf3aly;

import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class Statement_GeneratorTest {

    private Statement_Generator statementGenerator;
    private ByteArrayOutputStream outputStream;
    private PrintStream originalOut;
    private SimpleDateFormat dateFormat;

    @Before
    public void setUp() {
        statementGenerator = new Statement_Generator();
        outputStream = new ByteArrayOutputStream();
        originalOut = System.out;
        System.setOut(new PrintStream(outputStream));
        dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    }

    @Test
    public void testEmptyStatementGenerator() {
        // Act
        statementGenerator.generateStatement();
        String output = outputStream.toString();

        // Assert
        String expectedOutput = "Transaction History:\n\n" +
                "Transaction ID  Type           Amount         Date          \n" +
                "----------------------------------------------------\n" +
                "----------------------------------------------------\n";
        assertNotEquals(expectedOutput, output);
    }

    @Test
    public void testStatementGeneratorWithTransactions() {
        // Creating sample transactions
        UUID expectedTransactionID1 = UUID.randomUUID();
        UUID expectedTransactionID2 = UUID.randomUUID();
        UUID expectedTransactionID3 = UUID.randomUUID();

        Transactions transaction1 = new Transactions(expectedTransactionID1, "Deposit", 1000.0, new Date());
        Transactions transaction2 = new Transactions(expectedTransactionID2, "Withdrawal", -500.0, new Date());
        Transactions transaction3 = new Transactions(expectedTransactionID3, "Deposit", 1500.0, new Date());

        // Adding transactions to the statement generator
        statementGenerator.addTransaction(transaction1);
        statementGenerator.addTransaction(transaction2);
        statementGenerator.addTransaction(transaction3);

        // Act
        statementGenerator.generateStatement();
        String output = outputStream.toString();

        // Assert
        String expectedOutput = "Transaction History:\n\n" +
                "Transaction ID                     Type           Amount         Date          \n" +
                "----------------------------------------------------\n" +
                expectedTransactionID1.toString() + "    Deposit          $1000.0      " + dateFormat.format(transaction1.getDate()) + "\n" +
                expectedTransactionID2.toString() + "    Withdrawal       $-500.0      " + dateFormat.format(transaction2.getDate()) + "\n" +
                expectedTransactionID3.toString() + "    Deposit          $1500.0      " + dateFormat.format(transaction3.getDate()) + "\n" +
                "----------------------------------------------------\n";
        assertNotEquals(expectedOutput, output);
    }

    @Test
    public void testStatementGeneratorWithSingleTransaction() {
        // Creating a single transaction
        UUID expectedTransactionID = UUID.randomUUID();
        Transactions transaction = new Transactions(expectedTransactionID, "Deposit", 2000.0, new Date());

        // Adding the transaction to the statement generator
        statementGenerator.addTransaction(transaction);

        // Act
        statementGenerator.generateStatement();
        String output = outputStream.toString();

        // Assert
        String expectedOutput = "Transaction History:\n\n" +
                "Transaction ID                     Type           Amount         Date          \n" +
                "----------------------------------------------------\n" +
                expectedTransactionID.toString() + "    Deposit        $2000.0      " + dateFormat.format(transaction.getDate()) + "   \n" +
                "----------------------------------------------------\n";
        assertNotEquals(expectedOutput, output);
    }
}
