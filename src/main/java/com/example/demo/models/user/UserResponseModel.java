package com.example.demo.models.user;

import java.util.UUID;

public class UserResponseModel extends UserModel {

    private String uuid;

    public UserResponseModel() {
    }

    public UserResponseModel(String fullName, String userName, String uuid) {
        super(fullName, userName);
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
