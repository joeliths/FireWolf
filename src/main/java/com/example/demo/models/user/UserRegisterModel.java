package com.example.demo.models.user;

import java.util.List;

public class UserRegisterModel extends UserModel {

    private String password;

    public UserRegisterModel() {
    }

    public UserRegisterModel(String password) {
        this.password = password;
    }

    public UserRegisterModel(String fullName, String userName, String password) {
        super(fullName, userName);
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
