package com.example.demo.models.pendingorder;

import com.example.demo.models.pendingorder.nestedobjects.PendingOrderProductRequestModel;

import javax.persistence.PrePersist;
import java.util.List;
import java.util.UUID;

public class PendingOrderRequestModel {

    private String storeUUID;
    private List<PendingOrderProductRequestModel> orderedProducts;

    public PendingOrderRequestModel(){}

    public PendingOrderRequestModel(String storeUUID,
                                    List<PendingOrderProductRequestModel> orderedProducts) {
        this.storeUUID = storeUUID;
        this.orderedProducts = orderedProducts;
    }

    public String getStoreUUID() {
        return storeUUID;
    }

    public void setStoreUUID(String storeUUID) {
        this.storeUUID = storeUUID;
    }

    public List<PendingOrderProductRequestModel> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(List<PendingOrderProductRequestModel> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }


}
