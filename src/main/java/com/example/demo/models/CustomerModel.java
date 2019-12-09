package com.example.demo.models;

import com.example.demo.entities.Customer;
import com.example.demo.models.user.UserModel;

import java.io.Serializable;
import java.util.Set;

public class CustomerModel implements Serializable {

    public static final long serialVersionUID = 1L;

    private UserModel user;
    //private Set<PendingOrderModel> pendingOrders;


    public UserModel getUser() {
        return user;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }




}
