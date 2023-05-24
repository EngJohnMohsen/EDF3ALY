package com.example.edf3aly;
import java.util.ArrayList;
import java.util.Date;

public class main {
    
        public static void main(String[] args) {
            // Create ArrayLists for objects
            ArrayList<User> users = new ArrayList<>();
            ArrayList<Account> accounts = new ArrayList<>();
            ArrayList<Transactions> transactions = new ArrayList<>();
            ArrayList<Notifacations> Notifacationss = new ArrayList<>();
            ArrayList<Buy_Item> items = new ArrayList<>();
            ArrayList<Pay_Bills> Pay_Bills = new ArrayList<>();

            // Adding objects to ArrayLists

            User user1 = new User("name","123456","01112233344","john","pass");
            users.add(user1);

            /*private int transactionID;
    private String transactionType;
    private double amount;
    private Date date;*/
            Account account1 = new Account("1", "1000.0", 240.0);
            accounts.add(account1);

            Transactions transaction1 = new Transactions(1, "transfer",200.00,new Date());
            transactions.add(transaction1);

            Notifacations Notifacations1 = new Notifacations(1, "Transaction successful", new Date(), user1);
            Notifacationss.add(Notifacations1);

            Buy_Item item1 = new Buy_Item(1,200.0,"store1");
            items.add(item1);

            /* private int billID;
    private String billName;
    private boolean automatically;
    private int paymentDay;*/
            Pay_Bills Pay_Bills1 = new Pay_Bills(1,200,21,"bill n",true);
            Pay_Bills.add(Pay_Bills1);

            // Accessing objects from ArrayLists
            User retrievedUser = users.get(0);
            System.out.println("User: " + retrievedUser.getUsername());

            Account retrievedAccount = accounts.get(0);
            System.out.println("Account balance: " + retrievedAccount.getAccBalance());

            Transactions retrievedTransaction = transactions.get(0);
            System.out.println("Transaction amount: " + retrievedTransaction.getAmount());

            Notifacations retrievedNotifacations = Notifacationss.get(0);
            System.out.println("Notifacations message: " + retrievedNotifacations.getMessage());

            Buy_Item retrievedItem = items.get(0);
            System.out.println("Item name: " + retrievedItem.getStore());

            Pay_Bills retrievedPay_Bills = Pay_Bills.get(0);
            System.out.println("Pay_Bills amount: " + retrievedPay_Bills.getAmount());}
}
