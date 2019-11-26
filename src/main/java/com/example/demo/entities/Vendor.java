package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "vendor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    //@ManyToOne(mappedBy = "vendor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Store store;


    public Vendor() {
    }

    public Vendor(User user, Store store) {
        this.user = user;
        this.store = store;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

}
