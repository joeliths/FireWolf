package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Vendor implements Serializable, MyEntity {

    private static final long serialVersionUID = 1L;

    @Id
    private Long id;
    @OneToOne( fetch = FetchType.LAZY)
    @MapsId
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @Embedded
    private MyUUID uuid = new MyUUID();

    @OneToMany(mappedBy = "vendor", cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    private Set<Store> stores;


    public Vendor() {
    }

    public Long getId() {
        return id;
    }

    public Vendor(Set<Store> stores) {
        this.stores = stores;
    }

    public MyUUID getUuid() {
        return uuid;
    }

    public Set<Store> getStores() {
        return stores;
    }

    public void setStores(Set<Store> stores) {
        this.stores = stores;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
