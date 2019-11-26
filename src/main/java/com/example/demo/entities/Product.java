package com.example.demo.entities;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
public class Product implements Serializable{

    private static final long serialVersionUID = 1L;



    @GenericGenerator(name = "uuid2", strategy = "uuid2", parameters = {})
    @Type(type = "uuid-char")
    @Column(columnDefinition = "BINARY(16)")
    @GeneratedValue(generator = "uuid2")
    private UUID uuid;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="product_name", length=100, nullable=false)
    private String name;
    @Column(name="product_description", length=100, nullable=false)
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
}
