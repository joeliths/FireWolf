package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PendingOrder implements Serializable{

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
    @JoinColumn(name = "store_id")
    private Store store = new Store();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Customer customer;

    @OneToMany(
            mappedBy = "pendingOrder",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<PendingOrderProduct> pendingOrderProducts = new HashSet<>();

    @Column(name = "placement_date_time",nullable = false)
    private Date placemenDateTime;


    @Column(name = "expiration_date_time",nullable = false)
    private Date expirationDateTime;

    public PendingOrder(){

    }
    public PendingOrder(Date placemenDateTime, Date expirationDateTime) {
        this.placemenDateTime = placemenDateTime;
        this.expirationDateTime = expirationDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacemenDateTime() {
        return placemenDateTime;
    }

    public void setPlacemenDateTime(Date placemenDateTime) {
        this.placemenDateTime = placemenDateTime;
    }

    public Date getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(Date expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Set<PendingOrderProduct> getPendingOrderProducts() {
        return pendingOrderProducts;
    }

    public void setPendingOrderProducts(Set<PendingOrderProduct> pendingOrderProducts) {
        this.pendingOrderProducts = pendingOrderProducts;
    }

    @Override
    public String toString() {
        return "PendingOrder{" +
                "uuid=" + uuid +
                ", id=" + id +
                ", store=" + store +
                ", customer=" + customer.getUser().getUserName() +
                ", pendingOrderProducts=" + pendingOrderProducts +
                ", placemenDateTime=" + placemenDateTime +
                ", expirationDateTime=" + expirationDateTime +
                '}';
    }
}
