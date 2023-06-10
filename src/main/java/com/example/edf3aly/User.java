package com.example.edf3aly;

import java.util.InputMismatchException;
import java.util.List;

public class User {
    private String name;
    private String ssn;
    private String phoneNo;
    private String username;
    private String password;
    private  Account account;

    public User(String name, String ssn, String phoneNo, String username, String password,Account account)
    {
        this.name = name;
        this.ssn = ssn;
        this.phoneNo = phoneNo;
        this.username = username;
        this.password = password;
        this.account = account;
    }

    public String getName()
    {
        return name;
    }

    public String getSSN()
    {
        return ssn;
    }

    public String getPhoneNo()
    {
        return phoneNo;
    }

    public String getUsername()
    {
        return username;
    }

    public String getPassword()
    {
        return password;
    }

    public Account getAccount()
    {
        return account;
    }

    public void setAccount(Account account)
    {
        this.account = account;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }


    public boolean register(List<User> usersList) {
        for (User value : usersList) {
            if (value.getSSN().equals(this.getSSN())) {
                System.out.println("\nUser: " + this.getName() + " Already Exists\n");
                return false;
            }
        }
        usersList.add(this);
        System.out.println("\nUser: " + this.getName() + " Successfully Added\n");
        return true;
    }
    public boolean checkUsername() {
        if (username.isEmpty() || username.matches(".*\\d.*") || !username.matches("\\p{Alnum}+")) {
            throw new InputMismatchException("Invalid username");
        }
        return true;
    }

    public boolean checkPassword() {
        if (password.isEmpty() || password.length() < 6 || !password.matches(".*\\p{Alpha}.*")) {
            throw new InputMismatchException("Invalid password");
        }
        return true;
    }

}
