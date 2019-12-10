package com.example.demo.repositories;

import com.example.demo.entities.PendingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PendingOrderRepository extends JpaRepository<PendingOrder,Long> {


    @Query(value = "Select * from pending_order limit 1",nativeQuery = true)
    PendingOrder getTesting();

    @Query(nativeQuery = true, value = "select * from pending_order p where p.uuid = :uuid")
    PendingOrder findFirstByUuid(@Param("uuid") String uuid);


    @Query(nativeQuery = true, value = "select * from pending_order p where p.uuid = :uuid")
    Optional<PendingOrder> findByUuid(@Param("uuid") String uuid);

}
