package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class InventoryProduct implements Serializable{

    private static final long serialVersionUID = 1L;

    @Embedded
    private MyUUID uuid = new MyUUID();

    public MyUUID getUuid() {
        return uuid;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="stock", length=100, nullable=false)
    private int stock;
    @Column(name="price", length=100, nullable=false)
    private  int price;




    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product = new Product();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store = new Store();

    @OneToMany(mappedBy = "inventoryProduct",
            cascade = CascadeType.PERSIST,
    orphanRemoval = true)
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
