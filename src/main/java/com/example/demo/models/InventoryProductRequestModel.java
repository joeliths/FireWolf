package com.example.demo.models;

public class InventoryProductRequestModel {

    private Integer price;
    private Integer stock;

    public InventoryProductRequestModel() {
    }

    public InventoryProductRequestModel(int price, int stock) {
        this.price = price;
        this.stock = stock;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
