package com.example.demo.models.pendingorder;

import com.example.demo.models.pendingorder.nestedobjects.PendingOrderProductRequestModel;

import java.util.List;
import java.util.Set;

public class PendingOrderRequestModel {

    private String storeUUID;
    private Set<PendingOrderProductRequestModel> pendingOrderProducts;

    public PendingOrderRequestModel(){}

    public PendingOrderRequestModel(String storeUUID,
                                    Set<PendingOrderProductRequestModel> pendingOrderProducts) {
        this.storeUUID = storeUUID;
        this.pendingOrderProducts = pendingOrderProducts;
    }

    public String getStoreUUID() {
        return storeUUID;
    }

    public void setStoreUUID(String storeUUID) {
        this.storeUUID = storeUUID;
    }

    public Set<PendingOrderProductRequestModel> getPendingOrderProducts() {
        return pendingOrderProducts;
    }

    public void setPendingOrderProducts(Set<PendingOrderProductRequestModel> pendingOrderProducts) {
        this.pendingOrderProducts = pendingOrderProducts;
    }


}
