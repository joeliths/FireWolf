package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Store implements Serializable, MyEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private MyUUID uuid = new MyUUID();

    public MyUUID getUuid() {
        return uuid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @OnDelete(action =OnDeleteAction.CASCADE)
    @OneToOne(mappedBy = "store", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)
    private Position position;

    @OnDelete(action =OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST, orphanRemoval = true)
    Set<PendingOrder> pendingOrders;

    @OnDelete(action =OnDeleteAction.CASCADE)
    @OneToMany(mappedBy = "store", cascade = CascadeType.PERSIST, orphanRemoval = true)
    Set<InventoryProduct> inventoryProducts;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vendor_id")
    private Vendor vendor;

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

    public Vendor getVendor() {
        return vendor;
    }

    public void setVendor(Vendor vendor) {
        this.vendor = vendor;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Set<PendingOrder> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Set<PendingOrder> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public Set<InventoryProduct> getInventoryProducts() {
        return inventoryProducts;
    }

    public void setInventoryProducts(Set<InventoryProduct> inventoryProducts) {
        this.inventoryProducts = inventoryProducts;
    }
}
