package com.example.demo.models;

import java.io.Serializable;

public class InventoryProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private int stock;
    private int price;
    private ProductModel product;

    public ProductModel getProduct() {
        return product;
    }

    public void setProduct(ProductModel product) {
        this.product = product;
    }

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
