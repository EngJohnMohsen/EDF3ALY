package com.example.edf3aly;

import java.util.Date;

public class Pay_Bills extends Transactions {

    private int billID;
    private String billName;
    private boolean automatically;
    private int paymentDay;

    public Pay_Bills(int transactionID, double amount, int billID, String billName, boolean automatically)
    {
        super(transactionID, "Pay Bills", amount,new Date());
        this.billID = billID;
        this.billName = billName;
        this.automatically = automatically;
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
}
