package com.example.demo.models;

import java.io.Serializable;

public class StoreModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;
    private String description;
    private PositionModel position;

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

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }
}
