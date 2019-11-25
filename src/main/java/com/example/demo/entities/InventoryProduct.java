package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class InventoryProduct implements Serializable{
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getStoreId() {
        return storeId;
    }

    public void setStoreId(Long storeId) {
        this.storeId = storeId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getPendingOrderProductId() {
        return pendingOrderProductId;
    }

    public void setPendingOrderProductId(Long pendingOrderProductId) {
        this.pendingOrderProductId = pendingOrderProductId;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long storeId;
    private Long productId;
    private Long pendingOrderProductId;

    @Column(name="inventory_product_stock", length=100, nullable=false)
    private int stock;
    @Column(name="inventory_product_price", length=100, nullable=false)
    private  int price;
}
