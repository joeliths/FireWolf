package com.example.demo.models.user;

import java.io.Serializable;
import java.util.UUID;

public class UserResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String userName;
    private String uuid;

    public UserResponseModel() {
    }

    public UserResponseModel(String fullName, String userName, String uuid) {
        this.fullName = fullName;
        this.userName = userName;
        this.uuid = uuid;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
