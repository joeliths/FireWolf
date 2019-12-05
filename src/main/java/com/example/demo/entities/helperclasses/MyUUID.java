package com.example.demo.entities.helperclasses;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.PrePersist;
import java.util.UUID;

@Embeddable
public class MyUUID {

    @Column(columnDefinition = "varchar(50)")
    @Type(type = "uuid-char")
    private UUID uuid;

    @PrePersist
    public void initializeUUID() {
            uuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(String uuid){
        this.uuid = UUID.fromString(uuid);
    }

    public String toString() {
        return uuid.toString();
    }
}
