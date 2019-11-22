package com.example.demo.entities;

import javax.persistence.*;

@Entity(name = "store")
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //Vendor-koppling
    //PendingOrder-koppling
    //InventoryOrder-koppling

    private String address;
    private String description;

    public Store() {
    }

    public Store(String address, String description) {
        this.address = address;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
