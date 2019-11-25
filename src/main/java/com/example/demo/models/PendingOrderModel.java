package com.example.demo.models;


import java.io.Serializable;
import java.util.Date;

public class PendingOrderModel implements Serializable {

    private static final long serialVersionUID = 1L;

    // private StoreModel store;
    private Date placemenDateTime;
    private Date expirationDateTime;
}
