package com.example.demo.models.pendingorder.nestedobjects;

import com.example.demo.entities.helperclasses.MyUUID;

public class PendingOrderProductRequestModel {
    
    private MyUUID inventoryProductUUID;
    private int quantity;

    public PendingOrderProductRequestModel() {
    }

    public PendingOrderProductRequestModel(MyUUID inventoryProductUUID, int quantity) {
        this.inventoryProductUUID = inventoryProductUUID;
        this.quantity = quantity;
    }

    public MyUUID getInventoryProductUUID() {
        return inventoryProductUUID;
    }

    public void setInventoryProductUUID(MyUUID inventoryProductUUID) {
        this.inventoryProductUUID = inventoryProductUUID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
