package com.example.demo.models;

import java.io.Serializable;

public class ProductModel implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String description;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
