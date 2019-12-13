package com.example.demo.models;

import java.io.Serializable;

public class PositionModel implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long lat;
    private Long lng;

    public PositionModel(Long lat, Long lng) {
        this.lat = lat;
        this.lng = lng;
    }

    public PositionModel() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getLat() {
        return lat;
    }

    public void setLat(Long lat) {
        this.lat = lat;
    }

    public Long getLng() {
        return lng;
    }

    public void setLng(Long lng) {
        this.lng = lng;
    }
}
