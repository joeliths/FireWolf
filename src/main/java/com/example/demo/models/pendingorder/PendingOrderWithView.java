package com.example.demo.models.pendingorder;

import com.example.demo.models.CustomerModel;
import com.example.demo.models.PendingOrderProductModel;
import com.example.demo.models.StoreModel;
import com.example.demo.models.view.PendingOrderProductView;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PendingOrderWithView implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private StoreModel store;
    private CustomerModel customer;
    Set<PendingOrderProductView> pendingOrderProducts;
    private Date placementDateTime;
    private Date expirationDateTime;

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

    public Set<PendingOrderProductView> getPendingOrderProducts() {
        return pendingOrderProducts;
    }

    public void setPendingOrderProducts(Set<PendingOrderProductView> pendingOrderProducts) {
        this.pendingOrderProducts = pendingOrderProducts;
    }

    public Date getPlacementDateTime() {
        return placementDateTime;
    }

    public void setPlacementDateTime(Date placementDateTime) {
        this.placementDateTime = placementDateTime;
    }

    public Date getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(Date expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
    }
}
