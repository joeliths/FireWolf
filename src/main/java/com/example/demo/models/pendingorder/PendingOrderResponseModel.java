package com.example.demo.models.pendingorder;


import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.CustomerModel;
import com.example.demo.models.StoreModel;
import com.example.demo.models.pendingorder.nestedobjects.PendingOrderProductResponseModel;

import java.io.Serializable;
import java.util.List;

public class PendingOrderResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String orderUUID;
    private String placemenDateTime;
    private String expirationDateTime;
    private StoreModel store;
    private CustomerModel customer;
    private List<PendingOrderProductResponseModel> orderedProducts;

    public PendingOrderResponseModel() {
    }

    public PendingOrderResponseModel(String orderUUID, String placemenDateTime, String expirationDateTime,
                                     StoreModel store, CustomerModel customer,
                                     List<PendingOrderProductResponseModel> orderedProducts) {
        this.orderUUID = orderUUID;
        this.placemenDateTime = placemenDateTime;
        this.expirationDateTime = expirationDateTime;
        this.store = store;
        this.customer = customer;
        this.orderedProducts = orderedProducts;
    }

    public String getOrderUUID() {
        return orderUUID;
    }

    public void setOrderUUID(String orderUUID) {
        this.orderUUID = orderUUID;
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
        return orderedProducts;
    }

    public void setOrderedProducts(List<PendingOrderProductResponseModel> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
}
