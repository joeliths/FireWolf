package com.example.demo.models.pendingorder.nestedobjects;

import java.io.Serializable;

public class PendingOrderProductResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int priceAtOrderPlacementTime;
    private int quantity;

    public PendingOrderProductResponseModel() {
    }

    public PendingOrderProductResponseModel(String name, int priceAtOrderPlacementTime, int quantity) {
        this.name = name;
        this.priceAtOrderPlacementTime = priceAtOrderPlacementTime;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPriceAtOrderPlacementTime() {
        return priceAtOrderPlacementTime;
    }

    public void setPriceAtOrderPlacementTime(int priceAtOrderPlacementTime) {
        this.priceAtOrderPlacementTime = priceAtOrderPlacementTime;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
