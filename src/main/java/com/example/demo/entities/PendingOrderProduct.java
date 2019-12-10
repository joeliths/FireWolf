package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PendingOrderProduct implements Serializable, MyEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private MyUUID uuid = new MyUUID();

    public MyUUID getUuid() {
        return uuid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pendingOrder_id")
    PendingOrder pendingOrder;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "inventoryProduct_id")
    InventoryProduct inventoryProduct;

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

    public PendingOrder getPendingOrder() {
        return pendingOrder;
    }

    public void setPendingOrder(PendingOrder pendingOrder) {
        this.pendingOrder = pendingOrder;
    }

    public InventoryProduct getInventoryProduct() {
        return inventoryProduct;
    }

    public void setInventoryProduct(InventoryProduct inventoryProduct) {
        this.inventoryProduct = inventoryProduct;
    }

    @Override
    public String toString() {
        return "PendingOrderProduct{" +
                "uuid=" + uuid +
                ", id=" + id +
                ", pendingOrder=" + pendingOrder +
                ", inventoryProduct=" + inventoryProduct +
                ", quantity=" + quantity +
                '}';
    }
}
