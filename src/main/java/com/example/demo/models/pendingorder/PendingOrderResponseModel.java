package com.example.demo.models.pendingorder;


import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.CustomerModel;
import com.example.demo.models.StoreModel;
import com.example.demo.models.pendingorder.nestedobjects.PendingOrderProductResponseModel;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class PendingOrderResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;
    private String placemenDateTime;
    private String expirationDateTime;
    private StoreModel store;
    private CustomerModel customer;
    private List<PendingOrderProductResponseModel> pendingOrderProducts;

    public PendingOrderResponseModel() {
    }

    public PendingOrderResponseModel(String uuid, String placementDateTime, String expirationDateTime,
                                     StoreModel store, CustomerModel customer,
                                     List<PendingOrderProductResponseModel> pendingOrderProducts) {
        this.uuid = uuid;
        this.placemenDateTime = placementDateTime;
        this.expirationDateTime = expirationDateTime;
        this.store = store;
        this.customer = customer;
        this.pendingOrderProducts = pendingOrderProducts;
    }

    public String getuuid() {
        return uuid;
    }

    public void setuuid(String orderUUID) {
        this.uuid = orderUUID;
    }

    public String getPlacemenDateTime() {
        return placemenDateTime;
    }

    public void setPlacemenDateTime(String placemenDateTime) {
        this.placemenDateTime = placemenDateTime;
    }

    public String getExpirationDateTime() {
        return expirationDateTime;
    }

    public void setExpirationDateTime(String expirationDateTime) {
        this.expirationDateTime = expirationDateTime;
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

    public List<PendingOrderProductResponseModel> getOrderedProducts() {
        return pendingOrderProducts;
    }

    public void setOrderedProducts(List<PendingOrderProductResponseModel> pendingOrderProducts) {
        this.pendingOrderProducts = pendingOrderProducts;
    }
}
