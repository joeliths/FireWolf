package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class InventoryProduct implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="inventory_product_stock", length=100, nullable=false)
    private int stock;
    @Column(name="inventory_product_price", length=100, nullable=false)
    private  int price;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinTable(name = "inventoryproduct_pendingorderproduct",
            joinColumns=@JoinColumn(name="inventory_product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "pending_order_product_id", referencedColumnName = "id"))
    private Set<PendingOrderProduct> pendingOrderProducts = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

}
