package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class PendingOrder implements Serializable, MyEntity{

    private static final long serialVersionUID = 1L;

    @Embedded
    private MyUUID uuid = new MyUUID();

    public UUID getUuid() {
        return uuid.getUuid();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id", nullable = false)
    private Store store;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OnDelete(action = OnDeleteAction.CASCADE)
    @OneToMany(
            mappedBy = "pendingOrder",
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST, CascadeType.MERGE},
            orphanRemoval = true
    )
    private Set<PendingOrderProduct> pendingOrderProducts = new HashSet<>();

    @Column(name = "placement_date_time", nullable = false)
    private Date placementDateTime;


    @Column(name = "expiration_date_time",nullable = false)
    private Date expirationDateTime;

    public PendingOrder(){

    }
    public PendingOrder(Date placemenDateTime, Date expirationDateTime) {
        this.placementDateTime = placemenDateTime;
        this.expirationDateTime = expirationDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacementDateTime() {
        return placementDateTime;
    }

    public void setPlacemenDateTime(Date placemenDateTime) {
        this.placementDateTime = placemenDateTime;
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
        System.out.println(store.getAddress());this.store = store;
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

}
