package com.example.demo.entities;

import javax.persistence.*;

@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    //Vendor-koppling
    //PendingOrder-koppling
    //InventoryOrder-koppling

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
