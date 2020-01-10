package com.example.demo.models.pendingorder;

import com.example.demo.models.user.Customer.CustomerModel;
import com.example.demo.models.StoreModel;
import com.example.demo.models.view.PendingOrderProductView;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public class PendingOrderResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private StoreModel store;
    private CustomerModel customer;
    Set<PendingOrderProductView> pendingOrderProductsViews;
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

    public Set<PendingOrderProductView> getPendingOrderProductsViews() {
        return pendingOrderProductsViews;
    }

    public void setPendingOrderProductsViews(Set<PendingOrderProductView> pendingOrderProducts) {
        this.pendingOrderProductsViews = pendingOrderProducts;
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

    public CustomerModel getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerModel customerModel) {
        this.customer = customerModel;
    }
}
