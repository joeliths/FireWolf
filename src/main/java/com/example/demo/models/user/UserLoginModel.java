package com.example.demo.models.user;

import java.io.Serializable;

public class UserLoginModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String userName;
    private String password;

    public UserLoginModel() {
    }

    public UserLoginModel(String userName, String password) {
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

}
