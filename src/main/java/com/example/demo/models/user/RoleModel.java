package com.example.demo.models.user;

import java.io.Serializable;

public class RoleModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String role;

    public RoleModel() {
    }

    public RoleModel(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
