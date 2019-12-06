package com.example.demo.models.pendingorder;

import com.example.demo.entities.Store;
import com.example.demo.models.CustomerModel;
import com.example.demo.models.PendingOrderProductModel;
import com.example.demo.models.StoreModel;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PendingOrderModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private StoreModel store;
    private CustomerModel customer;
    Set<PendingOrderProductModel> pendingOrderProducts;
    private Date placemenDateTime;
    private Date expirationDateTime;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public StoreModel getStore() {
        return store;
    }

    public void setStore(StoreModel store) {
        this.store = store;
    }

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customer) {
        this.customer = customer;
    }

    public Set<PendingOrderProductModel> getPendingOrderProducts() {
        return pendingOrderProducts;
    }

    public void setPendingOrderProducts(Set<PendingOrderProductModel> pendingOrderProducts) {
        this.pendingOrderProducts = pendingOrderProducts;
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
