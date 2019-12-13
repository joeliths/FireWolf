package com.example.demo.models.user.Customer;

import com.example.demo.models.pendingorder.PendingOrderModel;

import java.io.Serializable;
import java.util.Set;

public class CustomerResponseModel extends CustomerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private Set<PendingOrderModel> pendingOrders;

    public Set<PendingOrderModel> getPendingOrders() {
        return pendingOrders;
    }

    public void setPendingOrders(Set<PendingOrderModel> pendingOrders) {
        this.pendingOrders = pendingOrders;
    }
}
