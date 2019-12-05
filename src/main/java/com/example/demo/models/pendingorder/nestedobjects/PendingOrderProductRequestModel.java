package com.example.demo.models.pendingorder.nestedobjects;

import com.example.demo.entities.helperclasses.MyUUID;

public class PendingOrderProductRequestModel {
    
    private String inventoryProductUUID;
    private int quantity;

    public PendingOrderProductRequestModel() {
    }

    public PendingOrderProductRequestModel(String inventoryProductUUID, int quantity) {
        this.inventoryProductUUID = inventoryProductUUID;
        this.quantity = quantity;
    }

    public String getInventoryProductUUID() {
        return inventoryProductUUID;
    }

    public void setInventoryProductUUID(String inventoryProductUUID) {
        this.inventoryProductUUID = inventoryProductUUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
