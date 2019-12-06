package com.example.demo.entities;


import com.example.demo.entities.helperclasses.MyUUID;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Customer implements Serializable, MyEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Embedded
    private MyUUID uuid = new MyUUID();

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<PendingOrder> pendingOrders;

    public Customer() {
    }

    public Customer(Set<PendingOrder> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public MyUUID getUuid() {
        return uuid;
    }

    public Long getId() {
        return id;
    }

    public Set<PendingOrder> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Set<PendingOrder> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
