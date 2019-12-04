package com.example.demo.models.pendingorder.nestedobjects;

import java.io.Serializable;

public class PendingOrderProductResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int quantity;

    public PendingOrderProductResponseModel() {
    }

    public PendingOrderProductResponseModel(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
