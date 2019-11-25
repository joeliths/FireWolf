package com.example.demo.models;

import java.io.Serializable;

public class PendingOrderProductModel implements Serializable {

    private int quantity;

    public PendingOrderProductModel() {
    }

    public PendingOrderProductModel(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
