package com.example.demo.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Position implements Serializable, MyEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name="lat")
    private Double lat;

    @Column(name="lng")
    private Double lng;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "store_id")
    private Store store;

    public Position() {
    }

    public Position(Double lat, Double lng) {
        this.lat = lat;
        this.lng = lng;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
