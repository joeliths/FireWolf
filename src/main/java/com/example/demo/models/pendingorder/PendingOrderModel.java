package com.example.demo.models.pendingorder;

import com.example.demo.entities.Store;
import com.example.demo.models.CustomerModel;
import com.example.demo.models.PendingOrderProductModel;
import com.example.demo.models.StoreModel;

import java.util.Date;
import java.util.Set;

public class PendingOrderModel {
    private String uuid;
    private StoreModel store;
    private CustomerModel customer;
    Set<PendingOrderProductModel> pendingOrderProducts;
    private Date placemenDateTime;
    private Date expirationDateTime;
}
