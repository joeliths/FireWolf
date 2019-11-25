package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class PendingOrderProduct implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //PendingOrder-koppling
    //InventoryOrder-koppling

    @Column(nullable=false)
    private int quantity;

    public PendingOrderProduct() {
    }

    public PendingOrderProduct(int quantity) {
        this.quantity = quantity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
