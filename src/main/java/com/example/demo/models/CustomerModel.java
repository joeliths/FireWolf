package com.example.demo.models;

import com.example.demo.entities.Customer;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;

import java.io.Serializable;
import java.util.Set;

public class CustomerModel implements Serializable {

    public static final long serialVersionUID = 1L;

    //private UserModel user;
    private Set<String> pendingOrders;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<String> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Set<String> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
}
