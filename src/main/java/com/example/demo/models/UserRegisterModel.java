package com.example.demo.models;

import java.util.List;

public class UserRegisterModel extends UserModel {

    private String password;
    private List<String> roles;

    public UserRegisterModel() {
    }

    public UserRegisterModel(String password, List<String> roles) {
        this.password = password;
        this.roles = roles;
    }

    public UserRegisterModel(String fullName, String userName, String password, List<String> roles) {
        super(fullName, userName);
        this.password = password;
        this.roles = roles;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
