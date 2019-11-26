package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PendingOrderProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pendingOrderProduct")
    Set<PendingOrder> pendingOrders;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "pOp_fk")
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
}
