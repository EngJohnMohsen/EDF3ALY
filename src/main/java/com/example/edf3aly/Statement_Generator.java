package com.example.edf3aly;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Statement_Generator {
        private List<Transactions> transactionHistory;

        public Statement_Generator()
        {
            this.transactionHistory = new ArrayList<Transactions>();
        }

        public void addTransaction(Transactions transaction)
        {
            this.transactionHistory.add(transaction);
        }

        public void generateStatement() {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            System.out.println("Transaction History:\n");
            System.out.printf("%-15s %-15s %-15s %-15s\n", "Transaction ID", "Type", "Amount", "Date");
            System.out.println("----------------------------------------------------");
            for (Transactions transaction : transactionHistory)
            {
                System.out.printf("%-15s %-15s $%-15.2f %-15s\n",
                        transaction.getTransactionID().toString(),
                        transaction.getTransactionType(),
                        transaction.getAmount(),
                        dateFormat.format(transaction.getDate()));

            }
            System.out.println("----------------------------------------------------");
        }
    }
