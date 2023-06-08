package com.example.edf3aly;

import java.util.Date;

public class Notifications {

    String message;
    public Notifications(int i, String message, Date date, User user1) {
        this.message = message;

    }

    public String getMessage() {
        return message;
    }
}
