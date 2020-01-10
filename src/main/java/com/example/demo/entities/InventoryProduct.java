package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class InventoryProduct implements Serializable, MyEntity{

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

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product = new Product();

    @OnDelete(action = OnDeleteAction.CASCADE)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store = new Store();

    @OnDelete(action = OnDeleteAction.CASCADE)
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

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Set<PendingOrderProduct> getPendingOrderProducts() {
        return pendingOrderProducts;
    }

    public void setPendingOrderProducts(Set<PendingOrderProduct> pendingOrderProducts) {
        this.pendingOrderProducts = pendingOrderProducts;
    }

}
