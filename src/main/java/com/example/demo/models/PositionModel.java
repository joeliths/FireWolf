package com.example.demo.models;

import java.io.Serializable;

public class PositionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Double lat;
    private Double lng;

    public PositionModel(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public PositionModel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
}
