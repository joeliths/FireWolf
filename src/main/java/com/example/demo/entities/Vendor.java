package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vendor implements Serializable, MyEntity {

    private static final long serialVersionUID = 1L;

    @Embedded
    private MyUUID uuid = new MyUUID();

    public MyUUID getUuid() {
        return uuid;
    }

    @Id
    private Long id;

    @OneToOne( fetch = FetchType.LAZY)
    @MapsId
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
