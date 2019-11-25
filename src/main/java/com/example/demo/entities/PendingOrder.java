package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class PendingOrder {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    //private Store store;
    //private Customer customer;
    //private PendingOrderProduct pendingOrderProduct;

    @Column(name = "placement_date_time",nullable = false)
    private Date placemenDateTime;


    @Column(name = "expiration_date_time",nullable = false)
    private Date expirationDateTime;

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

}
