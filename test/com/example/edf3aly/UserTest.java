package com.example.edf3aly;

import org.junit.jupiter.api.Test;

import java.util.InputMismatchException;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    // ******usernames
    @Test
    // checks that the input is rejected if it contains numbers
    public void testUsernameWithNumbers() {
        User test = new User("Mai", "555667777", "2021234567", "123456", "AMA123", null);
        assertThrows(InputMismatchException.class, test::checkUsername);
    }

    @Test
    // checks that the input is rejected if it contains symbols
    public void testUsernameWithSymbols() {
        User test = new User("Mai", "555667777", "2021234567", "###", "AMA123", null);
        assertThrows(InputMismatchException.class, test::checkUsername);
    }

    @Test
    // checks that the input is rejected if empty
    public void testEmptyUsername() {
        User test = new User("Mai", "555667777", "2021234567", "", "AMA123", null);
        assertThrows(InputMismatchException.class, test::checkUsername);
    }

    @Test
    // normal case
    public void testValidUsername() {
        User test = new User("Mai", "555667777", "2021234567", "Maiiii", "AMA123", null);
        assertTrue(test.checkUsername());
    }

    // *******passwords
    @Test
    // checks that the input is rejected if it is shorter than 6 digits
    public void testShortPassword() {
        User test = new User("Mai", "555667777", "2021234567", "Maiiii", "AMA12", null);
        assertThrows(InputMismatchException.class, test::checkPassword);
    }

    @Test
    // checks that the input is rejected if it contains symbols only
    public void testPasswordWithSymbols() {
        User test = new User("Mai", "555667777", "2021234567", "Maiiii", "##@@@@", null);
        assertThrows(InputMismatchException.class, test::checkPassword);
    }

    @Test
    // checks that the input is rejected if empty
    public void testEmptyPassword() {
        User test = new User("Mai", "555667777", "2021234567", "Maiiii", "", null);
        assertThrows(InputMismatchException.class, test::checkPassword);
    }

    @Test
    // normal case
    public void testValidPassword() {
        User test = new User("Mai", "555667777", "2021234567", "Maiiii", "AMA123", null);
        assertTrue(test.checkPassword());
    }
}
