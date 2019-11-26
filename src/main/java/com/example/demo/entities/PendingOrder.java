package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PendingOrder implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "pendingorder_store",
            joinColumns=@JoinColumn(name="pendingorder_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "store_id", referencedColumnName = "id"))
    private Store store = new Store();

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinTable(name = "pendingorder_customer",
            joinColumns=@JoinColumn(name="pendingorder_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"))
    private Customer customer = new Customer();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "pendingorders")
    private Set<PendingOrderProduct> pendingOrderProducts = new HashSet<>();

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
