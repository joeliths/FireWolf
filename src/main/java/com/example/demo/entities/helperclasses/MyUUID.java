package com.example.demo.entities.helperclasses;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.PrePersist;
import java.util.UUID;

@Embeddable
public class MyUUID {
    @Column(name = "uuid")
    private UUID uuid;


    @PrePersist
    public void initializeUUID() {
            uuid = UUID.randomUUID();
    }

    public String getUuid() {
         return uuid.toString();
    }
    public String toString() {
        return uuid.toString();
    }
}
