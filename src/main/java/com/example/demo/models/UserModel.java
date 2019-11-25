package com.example.demo.models;

import java.io.Serializable;

public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String userName;

    public UserModel() {
    }

    public UserModel(String fullName, String userName) {
        this.fullName = fullName;
        this.userName = userName;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    
}
