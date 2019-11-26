package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class Vendor implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(mappedBy = "vendor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private User user;

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Store> stores;


    public Vendor() {
    }

    public Vendor(User user, Set<Store> stores) {
        this.user = user;
        this.stores = stores;
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

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }
}
