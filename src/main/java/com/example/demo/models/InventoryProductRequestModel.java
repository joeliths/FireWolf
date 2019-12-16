package com.example.demo.models;

public class InventoryProductRequestModel {

    private int price;
    private int stock;

    public InventoryProductRequestModel() {
    }

    public InventoryProductRequestModel(int price, int stock) {
        this.price = price;
        this.stock = stock;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
}
