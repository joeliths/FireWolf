package com.example.demo.models;

import java.io.Serializable;

public class StoreModel implements Serializable {

    private String address;
    private String description;

    public StoreModel() {
    }

    public StoreModel(String address, String description) {
        this.address = address;
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
