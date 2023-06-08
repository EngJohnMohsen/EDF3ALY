package com.example.edf3aly;
import static org.junit.Assert.*;
import org.junit.Test;

import java.util.Date;

public class NotificationsTest {

    Account account = new Account(Account.AccountType.Savings, "1001-1234", 600.0);


    @Test
    public void testGetMessage() {

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);


        Date date = new Date();


        Notifications notification = new Notifications(1, "Transaction successful", date, user);


        String message = notification.getMessage();

        assertEquals("Transaction successful", message);
    }

    @Test
    public void testGetMessageEmpty() {//pass

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);


        Date date = new Date();


        Notifications notification = new Notifications(1, "", date, user);


        String message = notification.getMessage();

        assertEquals("", message);
    }

    @Test
    public void testGetMessageNull() {

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);


        Date date = new Date();


        Notifications notification = new Notifications(1, null, date, user);


        String message = notification.getMessage();

        assertNull(message);
    }

    @Test
    public void testGetMessageWithSpecialCharacters() {

        User user = new User("Ahmed", "12345", "0115671212", "Acc12", "123456", account);


        Date date = new Date();

        Notifications notification = new Notifications(1, "Special characters: @#$%", date, user);


        String message = notification.getMessage();

        assertEquals("Special characters: @#$%", message);
}


}