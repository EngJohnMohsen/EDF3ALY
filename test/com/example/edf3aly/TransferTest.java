package com.example.edf3aly;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.InputMismatchException;

 class TransferTest {

    //TC1
    @Test
    public void transfer1(){

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
        user.setBalance(1400);

        Transfer testing = new Transfer(1, 200.0, "Acc");
        testing.setUser(user);

        assertEquals(true, testing.transferMoney());
    }
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
         User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456");
         user.setBalance(1400);

         Transfer testing = new Transfer(1, Double.parseDouble("4$#^"), "%#@");
         testing.setUser(user);

         assertThrows(InputMismatchException.class, () -> {
             testing.transferMoney();
         });
     }
}

