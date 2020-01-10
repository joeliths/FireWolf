package com.example.demo.models.user.Customer;

import java.io.Serializable;

public class CustomerModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String uuid;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
