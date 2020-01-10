package com.example.demo.models;

import com.example.demo.entities.InventoryProduct;

import java.io.Serializable;

public class PendingOrderProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int quantity;

    public InventoryProductModel inventoryProduct;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public InventoryProductModel getInventoryProduct() {
        return inventoryProduct;
    }

    public void setInventoryProduct(InventoryProductModel inventoryProduct) {
        this.inventoryProduct = inventoryProduct;
    }

    public PendingOrderProductModel() {
    }

    public PendingOrderProductModel(int quantity, InventoryProductModel inventoryProduct) {
        this.quantity = quantity;
        this.inventoryProduct = inventoryProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
