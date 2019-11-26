package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Store implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST, orphanRemoval = true)
    Set<PendingOrder> pendingOrders;

    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST, orphanRemoval = true)
    Set<InventoryProduct> inventoryProducts;

    @Column(nullable=false, length=100)
    private String address;
    @Column
    private String description;

    public Store() {
    }

    public Store(String address, String description) {
        this.address = address;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
