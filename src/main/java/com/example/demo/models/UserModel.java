package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class UserModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String userName;
    @JsonIgnore
    private String password;

    public UserModel() {
    }

    public UserModel(String fullName, String userName, String password) {
        this.fullName = fullName;
        this.userName = userName;
        this.password = password;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
