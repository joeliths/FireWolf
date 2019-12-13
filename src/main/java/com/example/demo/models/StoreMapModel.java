package com.example.demo.models;

import com.example.demo.entities.Position;

import java.io.Serializable;

public class StoreMapModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private PositionModel position;
    private String storeUUid;
    private String title;

    public StoreMapModel(PositionModel position, String storeUUid, String title) {
        this.position = position;
        this.storeUUid = storeUUid;
        this.title = title;
    }

    public StoreMapModel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public String getStoreUUid() {
        return storeUUid;
    }

    public void setStoreUUid(String storeUUid) {
        this.storeUUid = storeUUid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
