package com.example.demo.entities.helperclasses;

import javax.persistence.Column;
import javax.persistence.PrePersist;
import java.util.Date;

public class myOrderDateTime {

    @Column(name = "placement_date_time",nullable = false)
    private Date placemenDateTime;

    @Column(name = "expiration_date_time",nullable = false)
    private Date expirationDateTime;

    @PrePersist
    public void initializeUUID() {
    }

    public String getUuid() {
        return "";
    }

}
