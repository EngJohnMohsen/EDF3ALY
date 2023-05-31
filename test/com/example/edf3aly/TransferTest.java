package com.example.edf3aly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.InputMismatchException;

class TransferTest { // will add an update balance test later

    //TC1
    @Test
    public void transfer1() {
        Account sourceAccount = new Account("Normal", "1001-1234", 600.0);
        Account targetAccount = new Account("Normal", "1001-2345", 0.0); // Set the target account to the desired account

        System.out.println("This is the balance before: " + sourceAccount.getAccBalance());
        System.out.println("This is the balance before: " + targetAccount.getAccBalance());

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", sourceAccount);

        Transfer testing = new Transfer(1, 200.0, targetAccount,sourceAccount); // Set the target account as an Account object
        testing.setUser(user);

        assertTrue(testing.transferMoney());
        System.out.println("This is the remaining balance: " + sourceAccount.getAccBalance());
        System.out.println("This is the balance after: " + targetAccount.getAccBalance());
    }



    /*
    //TC2
    @Test
    public void transfer2() {
        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
        user.setBalance(1400);

        Transfer testing = new Transfer(1, 200.0, "Acc");
        testing.setUser(user);

        assertEquals(true, testing.transferMoney());
    }

    //TC3
    @Test
    public void transfer3() {
        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
        user.setBalance(1400);

        Transfer testing = new Transfer(1, 1400.0, "Acc");
        testing.setUser(user);

        assertEquals(true, testing.transferMoney());
    }

    //TC4
    @Test
    public void transfer4() {
        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
        user.setBalance(1400);

        Transfer testing = new Transfer(1, 1000.0, "");
        testing.setUser(user);

        assertThrows(InputMismatchException.class, () -> {testing.transferMoney();});
    }

     //TC5
     @Test
     public void transfer5() {
         User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
         user.setBalance(1400);

         Transfer testing = new Transfer(1, 1000.0, "hddhd");
         testing.setUser(user);

         assertThrows(InputMismatchException.class, () -> {
             testing.transferMoney();
         });
     }

     //TC6
     @Test
     public void transfer6() {
         User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
         user.setBalance(1400);

         Transfer testing = new Transfer(1, 1000.0, "12");
         testing.setUser(user);

         assertThrows(InputMismatchException.class, () -> {
             testing.transferMoney();
         });
     }

     //TC7
     @Test
     public void transfer7() {
         User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
         user.setBalance(1400);

         Transfer testing = new Transfer(1, 1000.0, "%#@" );
         testing.setUser(user);

         assertThrows(InputMismatchException.class, () -> {
             testing.transferMoney();
         });
     }

     //TC8
     @Test
     public void transfer8() {
         User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
         user.setBalance(1400);

         Transfer testing = new Transfer(1, Double.parseDouble("jjll"), "%#@" );
         testing.setUser(user);

         assertThrows(InputMismatchException.class, () -> {
             testing.transferMoney();
         });
     }

     //TC9
     @Test
     public void transfer9() {
         User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
         user.setBalance(1400);

         Transfer testing = new Transfer(1, Double.parseDouble(""), "%#@" );
         testing.setUser(user);

         assertThrows(InputMismatchException.class, () -> {
             testing.transferMoney();
         });
     }

     //TC10
     @Test
     public void transfer10() {
         User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456",new Account("acc1","123456",1400));
         user.setBalance(1400);

         Transfer testing = new Transfer(1, Double.parseDouble("4$#^"), "%#@");
         testing.setUser(user);

         assertThrows(InputMismatchException.class, () -> {
             testing.transferMoney();
         });
     }*/
}

