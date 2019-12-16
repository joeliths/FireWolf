package com.example.demo.models;

import java.io.Serializable;

public class StoreMapModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private PositionModel position;
    private String uuid;
    private String description;

    public StoreMapModel(PositionModel position, String uuid, String description) {
        this.position = position;
        this.uuid = uuid;
        this.description = description;
    }

    public StoreMapModel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }



    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PositionModel getPosition() {
        return position;
    }

    public void setPosition(PositionModel position) {
        this.position = position;
    }
}
