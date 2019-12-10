package com.example.demo.models;

import com.example.demo.models.pendingorder.PendingOrderModel;

import java.io.Serializable;
import java.util.Set;

public class CustomerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    //private UserModel user;
    private Set<PendingOrderModel> pendingOrders;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Set<PendingOrderModel> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Set<PendingOrderModel> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
}
