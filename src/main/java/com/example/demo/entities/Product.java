package com.example.demo.entities;

import com.example.demo.entities.helperclasses.MyUUID;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product implements Serializable, MyEntity{

    private static final long serialVersionUID = 1L;

    @Embedded
    private MyUUID uuid = new MyUUID();

    public Product(){
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", length=100, nullable=false)
    private String name;
    @Column(name="description", length=100, nullable=false)
    private  String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public MyUUID getUuid() {
        return uuid;
    }
}
