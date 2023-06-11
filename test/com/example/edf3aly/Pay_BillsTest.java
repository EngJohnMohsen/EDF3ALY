package com.example.edf3aly;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class Pay_BillsTest {

    @BeforeEach
    void setUp() {
        System.out.println("Starting Test Case...");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Test Case Ended...\n------------------");

    }


    //   SETTERS AND GETTERS      **/
    @Test
    public void testGettersAndSetters() {
        // Create an instance of Pay_Bills

        UUID transactionID = UUID.randomUUID();

        Pay_Bills payBills = new Pay_Bills(transactionID, 100.0,  "Electricity", true);

        // Use the getter methods to retrieve the values
        String billName = payBills.getBillName();
        boolean automatically = payBills.isAutomatically();

        // Assert the expected values
        assertEquals("Electricity", billName);
        assertTrue(automatically);
    }


    //   SETTERS AND GETTERS Payment   **/
    @Test
    public void testRepeatedPayment() {
        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 100.0,  "Electricity", true);

        // Call the repeatedPayment method
        int paymentDay = 15;
        payBills.repeatedPayment(paymentDay);

        // Get the payment day
        int actualPaymentDay = payBills.getPaymentDay();

        // Assert the expected payment day
        assertEquals(paymentDay, actualPaymentDay);
    }


    //   SETTERS AND GETTERS  Bill_Name    **/
    @Test
    public void testSetAndGetBillName() {
        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 100.0,  "", true);

        // Set the bill name
        String billName = "Electricity";
        payBills.setBillName(billName);

        // Get the bill name
        String actualBillName = payBills.getBillName();

        // Assert the expected bill name
        assertEquals(billName, actualBillName);
    }


    //   SETTERS AND GETTERS  Bill_ID    **/


    /**   Automatically     **/
    @Test
    public void testIsAutomatically() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        // Create a Pay_Bills instance with automatically set to true
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills1 = new Pay_Bills(transactionID, 200.0,  "Electricity", true);
        payBills1.setAccount(account);
        UUID transactionID2= UUID.randomUUID();
        // Create a Pay_Bills instance with automatically set to false
        Pay_Bills payBills2 = new Pay_Bills(transactionID2, 150.0,  "Water", false);
        payBills2.setAccount(account);

        // Verify the isAutomatically() method for payBills1
        assertTrue(payBills1.isAutomatically());

        // Verify the isAutomatically() method for payBills2
        assertFalse(payBills2.isAutomatically());
    }


    /**   PaymentDay Test     **/
    @Test
    public void Test_PaymentDay_0() {
        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 100.0,  "Electricity", true);

        // Set the payment day
        int paymentDay = 15;
        payBills.setPaymentDay(paymentDay);

        // Check if the payment day is set correctly
        int actualPaymentDay = payBills.getPaymentDay();

        // Assert the expected payment day
        assertEquals(paymentDay, actualPaymentDay);
    }

    @Test
    public void Test_PaymentDay_1() {
        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 100.0,  "Internet", true);
        // Set the payment day
        int paymentDay = 20;
        payBills.setPaymentDay(paymentDay);

        // Check if the payment day is set correctly
        int actualPaymentDay = payBills.getPaymentDay();

        // Assert the expected payment day
        assertEquals(paymentDay, actualPaymentDay);
    }


    /**   TC amount & TOTAL amount  Pay-bills     **/
    @Test
    public void testPaymentAmount_0() {
        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 100.0, "Electricity", true);

        // Get the payment amount
        double paymentAmount = payBills.getAmount();

        // Assert the expected payment amount
        assertEquals(100.0, paymentAmount, 0.0);
    }

    @Test
    public void testTotalAmountPaid() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        // Create multiple instances of Pay_Bills with different payment amounts
        UUID transactionID = UUID.randomUUID();
        UUID transactionID2 = UUID.randomUUID();
        UUID transactionID3 = UUID.randomUUID();
        Pay_Bills payBills1 = new Pay_Bills(transactionID, 200.0,  "Electricity", true);
        Pay_Bills payBills2 = new Pay_Bills(transactionID2, 150.0,  "Water"      , true);
        Pay_Bills payBills3 = new Pay_Bills(transactionID3, 100.0, "Internet"   , true);

        // Modify the payment day
        int paymentDay = 11;

        // Set the modified payment day
        payBills1.setPaymentDay(paymentDay);
        payBills2.setPaymentDay(paymentDay);
        payBills3.setPaymentDay(paymentDay);

        // Set the account for the payments
        payBills1.setAccount(account);
        payBills2.setAccount(account);
        payBills3.setAccount(account);

        // Perform the payments
        payBills1.performTransaction();
        payBills2.performTransaction();
        payBills3.performTransaction();

        // Get the total amount paid
        double totalAmountPaid = Pay_Bills.getTotalAmountPaid();

        // Assert the expected total amount paid
        assertEquals(450.0, totalAmountPaid, 0.0);
    }


    /**   TC Balance After Pay-bills @ the specific dates 1 payment to multi payments     **/
    @Test
    public void Test_AccountBalance_AfterPayment_PaymentDay_01() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456",account);

        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 200.0, "Electricity", true);

        // Modify the payment day
        int paymentDay = 11;

        // Set the modified payment day
        payBills.setPaymentDay(paymentDay);

        // Set the account for the payment
        payBills.setAccount(account);

        // Perform the payment
        payBills.performTransaction();

        // Get the updated account balance
        double updatedBalance = account.getAccBalance();

        // Assert the expected account balance
        assertEquals(300.0, updatedBalance, 0.0);
    }

    @Test
    public void Test_AccountBalance_AfterPayment_PaymentDay_11() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456",account);

        // Create an instance of Pay_Bills
        UUID transactionID = UUID.randomUUID();
        Pay_Bills payBills = new Pay_Bills(transactionID, 400.0,  "Electricity", true);

        // Modify the payment day
        int paymentDay = 11;

        // Set the modified payment day
        payBills.setPaymentDay(paymentDay);

        // Set the account for the payment
        payBills.setAccount(account);

        // Perform the payment
        payBills.performTransaction();

        // Get the updated account balance
        double updatedBalance = account.getAccBalance();

        // Assert the expected account balance
        assertEquals(100.0, updatedBalance, 0.0);
    }

    @Test
    // Many bills @ same date
    public void Test_AccountBalance_AfterPayment_PaymentDay_0M() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);

        // Create an instance of Pay_Bills for the first bill
        UUID transactionID = UUID.randomUUID();
        Pay_Bills bill1 = new Pay_Bills(transactionID, 200.0,  "Electricity", true);
        bill1.setPaymentDay(11);
        bill1.setAccount(account);

        // Perform the payment for bill1
        bill1.performTransaction();

        // Create an instance of Pay_Bills for the second bill
        UUID transactionID2 = UUID.randomUUID();
        Pay_Bills bill2 = new Pay_Bills(transactionID2, 150.0,  "Internet", true);
        bill2.setPaymentDay(11);
        bill2.setAccount(account);

        // Perform the payment for bill2
        bill2.performTransaction();
        UUID transactionID3 = UUID.randomUUID();
        Pay_Bills bill3 = new Pay_Bills(transactionID3, 150.0,  "Telephone", true);
        bill3.setPaymentDay(11);
        bill3.setAccount(account);

        // Perform the payment for bill3
        bill3.performTransaction();

        // Get the updated account balance
        double updatedBalance = account.getAccBalance();

        // Assert the expected account balance after both payments
        assertEquals(0.0, updatedBalance, 0.0);
    }

    @Test
    public void testAccountBalanceAfterMultiplePayments_1M() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);

        // Create an instance of Pay_Bills for the first bill
        UUID transactionID = UUID.randomUUID();
        Pay_Bills bill1 = new Pay_Bills(transactionID, 200.0,  "Electricity", true);
        bill1.setPaymentDay(11);
        bill1.setAccount(account);
        try {
            // Perform the payment for bill1
            bill1.performTransaction();
        } catch (IllegalArgumentException e) {
            // Handle the exception (payment day not reached)
            System.out.println("Payment day not reached. Ignoring the payment for bill1.");
        }

        // Create an instance of Pay_Bills for the second bill
        UUID transactionID2 = UUID.randomUUID();
        Pay_Bills bill2 = new Pay_Bills(transactionID2, 150.0,  "Internet", true);
        bill2.setPaymentDay(15);
        bill2.setAccount(account);

        try {
            // Perform the payment for bill2
            bill2.performTransaction();
        } catch (IllegalArgumentException e) {
            // Handle the exception (payment day not reached)
            System.out.println("Payment day not reached. Ignoring the payment for bill2.");
        }
        // Get the updated account balance
        double updatedBalance = account.getAccBalance();

        // Assert the expected account balance after both payments
        assertNotEquals(150.0, updatedBalance, 0.0);
        assertEquals(300.0, updatedBalance, 0.0);

    }

    //adding auto payment
    @Test
    public void testAccountBalanceAfterMultiplePayments_2M() {
        // Create an instance of Account
        Account account = new Account(Account.AccountType.Savings, "1001-1234", 500.0);

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);


        // Create an instance of Pay_Bills for the first bill
        UUID transactionID = UUID.randomUUID();
        Pay_Bills bill1 = new Pay_Bills(transactionID, 200.0,  "Electricity", false);
        bill1.setPaymentDay(11);
        bill1.setAccount(account);
        try {
            // Perform the payment for bill1
            bill1.performTransaction();
        } catch (IllegalArgumentException e) {
            if(bill1.isAutomatically()){
                // Handle the exception (payment day not reached)
                System.out.println("Payment day not reached. Ignoring the payment for bill 1.");
            }else System.out.println("Automatically payment isn't activated");
        }

        // Create an instance of Pay_Bills for the second bill
        UUID transactionID2 = UUID.randomUUID();
        Pay_Bills bill2 = new Pay_Bills(transactionID2, 150.0,  "Internet", true);
        bill2.setPaymentDay(15);
        bill2.setAccount(account);

        try {
            // Perform the payment for bill2
            bill2.performTransaction();
        } catch (IllegalArgumentException e) {
            if(bill2.isAutomatically()){
                // Handle the exception (payment day not reached)
                System.out.println("Payment day not reached. Ignoring the payment for bill 2.");
            }else System.out.println("Automatically payment isn't activated");
        }
        // Get the updated account balance
        double updatedBalance = account.getAccBalance();

        // Assert the expected account balance after both payments
        assertNotEquals(150.0, updatedBalance, 0.0);
        assertEquals(500, updatedBalance,0.0);

}

}