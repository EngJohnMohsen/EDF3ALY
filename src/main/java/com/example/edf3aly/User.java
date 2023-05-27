package com.example.edf3aly;

public class User {
    private String name;
    private String ssn;
    private String phoneNo;
    private String username;
    private String password;
    private Account account;


    private static double Balance;

    public User(String name, String ssn, String phoneNo, String username, String password)
    {
        this.name = name;
        this.ssn = ssn;
        this.phoneNo = phoneNo;
        this.username = username;
        this.password = password;
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

    public static double getBalance() {
        return Balance;
    }

    public static void setBalance(double balance) {
        Balance = balance;
    }

}
