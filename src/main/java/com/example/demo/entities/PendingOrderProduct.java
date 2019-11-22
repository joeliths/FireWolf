package com.example.demo.entities;

import javax.persistence.*;

@Entity
@Table(name = "pending_order_product")
public class PendingOrderProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //PendingOrder-koppling
    //InventoryOrder-koppling


    @Column(name="quantity", nullable=false)
    private int quantity;

    public PendingOrderProduct() {
    }

    public PendingOrderProduct(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
