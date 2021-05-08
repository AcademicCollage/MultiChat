package com.example.multichat.objects;

import android.util.Log;

public class User {
    private String userName,password;

    public User() {
    }

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals (Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            User employee = (User) object;
            result = this.userName.equals(employee.getUserName()) && this.password.equals(employee.getPassword());
        }
        return result;
    }
}
