package com.example.demo.models;

import java.io.Serializable;
import java.util.Set;

public class VendorModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private String uuid;
    private Set<StoreModel> stores;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Set<StoreModel> getStores() {
        return stores;
    }

    public void setStores(Set<StoreModel> stores) {
        this.stores = stores;
    }
}
