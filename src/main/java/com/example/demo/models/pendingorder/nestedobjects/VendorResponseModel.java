package com.example.demo.models.pendingorder.nestedobjects;

import java.io.Serializable;

public class VendorResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fullName;
    private String userName;

    public VendorResponseModel() {
    }

    public VendorResponseModel(String fullName, String userName) {
        this.fullName = fullName;
        this.userName = userName;
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

}
