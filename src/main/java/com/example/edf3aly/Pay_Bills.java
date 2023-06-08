package com.example.edf3aly;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

public class Pay_Bills extends Transactions {
    private static double totalAmountPaid = 0.0;
    private int billID;
    private String billName;
    private boolean automatically;
    private int paymentDay;
    private Account account; // Add account field
    public Pay_Bills(UUID transactionID, double amount, int billID, String billName, boolean automatically)
    {
        super(transactionID, "Pay_Bills", amount,new Date());
        this.billID = billID;
        this.billName = billName;
        this.automatically = automatically;
        // Update the total amount paid
        totalAmountPaid += amount;
    }

    // Other class methods

    public static double getTotalAmountPaid() {
        return totalAmountPaid;
    }

    public int getBillID()
    {
        return billID;
    }

    public void setBillID(int billID)
    {
        this.billID = billID;
    }

    public String getBillName()
    {
        return billName;
    }

    public void setBillName(String billName)
    {
        this.billName = billName;
    }

    public boolean isAutomatically()
    {
        return automatically;
    }

    public void setAutomatically(boolean automatically)
    {
        this.automatically = automatically;
    }

    public int getPaymentDay()
    {
        return paymentDay;
    }

    public void setPaymentDay(int paymentDay)
    {
        this.paymentDay = paymentDay;
    }

    public void repeatedPayment(int paymentDay)
    {
        this.paymentDay = paymentDay;
        System.out.println("Repeated payment set for day " + paymentDay);
    }

    public void setUser(User user) {
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public void performTransaction() {
        // Check if the account is valid
        if (account == null) {
            throw new IllegalStateException("No account associated with the transaction.");
        }

        // Check if the account has sufficient balance for the payment
        if (account.getAccBalance() < getAmount()) {
            throw new IllegalStateException("Insufficient balance in the account.");
        }

        // Check if it is the payment day
        int currentDay = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        if (isAutomatically() && currentDay == paymentDay) {
            // Process the payment
            if (account.getAccBalance() >= amount) {
                account.newBalance(account.getAccBalance(), amount, getTransactionType());
                System.out.println("Payment successful for bill ID: " + billID);
            } else {
                System.out.println("Insufficient funds in the account for bill ID: " + billID);
            }
        } else {
            throw new IllegalArgumentException("Payment day not reached for bill ID: " +
                    "Or Automatically payment isn't activated" + billID);
        }

}
}
