package com.example.demo.models;

import java.io.Serializable;

public class InventoryProductModel implements Serializable {
    private int stock;
    private int price;

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
