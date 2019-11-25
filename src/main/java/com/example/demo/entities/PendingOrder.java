package com.example.demo.entities;



import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.persistence.*;
import java.io.Serializable;
import java.io.StringReader;
import java.util.Date;

@Entity
public class PendingOrder implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private Customer customer;

    @Column(name = "placement_date_time")
    private Date placemenDateTime;


    @Column(name = "expiration_date_time")
    private Date expirationDateTime;


    public PendingOrder toEntity(String pendingOrderModel) {
        JsonReader reader = Json.createReader(new StringReader(pendingOrderModel));

        JsonObject jsonObject = reader.readObject();

        PendingOrder pendingOrder = new PendingOrder();

        //TODO set values to keys

        return pendingOrder;
    }


}
