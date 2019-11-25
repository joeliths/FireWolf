package com.example.demo.entities;


import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table


public class Customer {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //private User user;
    //private PendingOrder pendingOrder;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
