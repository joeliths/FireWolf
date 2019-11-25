package com.example.demo.entities;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.*;
import javax.security.auth.Subject;
import java.io.Serializable;
import java.io.StringReader;

@Entity
@Table


public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private User user;

    private PendingOrder pendingOrder;

    public Customer toEntity(String customerModel) {
        JsonReader reader = Json.createReader(new StringReader(customerModel));

        JsonObject jsonObject = reader.readObject();

        Customer customer = new Customer();

        //TODO set values to keys

        return customer;
    }

}
