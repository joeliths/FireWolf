package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long inventoryProductId;
    @Column(name="product_name", length=100, nullable=false)
    private String name;
    @Column(name="product_description", length=100, nullable=false)
    private  String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getInventoryProductId() {
        return inventoryProductId;
    }

    public void setInventoryProductId(Long inventoryProductId) {
        this.inventoryProductId = inventoryProductId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
