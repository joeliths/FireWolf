package com.example.demo.models.pendingorder.nestedobjects;

import java.io.Serializable;

public class StoreResponseModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String address;
    private String description;
    private VendorResponseModel vendor;

    public StoreResponseModel() {
    }

    public StoreResponseModel(String address, String description, VendorResponseModel vendor) {
        this.address = address;
        this.description = description;
        this.vendor = vendor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public VendorResponseModel getVendor() {
        return vendor;
    }

    public void setVendor(VendorResponseModel vendor) {
        this.vendor = vendor;
    }
}
