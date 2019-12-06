package com.example.demo.repositories;

import com.example.demo.entities.PendingOrder;
import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PendingOrderRepository extends JpaRepository<PendingOrder,Long> {


    @Query(value = "Select * from pending_order limit 1",nativeQuery = true)
    PendingOrder getTesting();

    PendingOrder findFirstByUuid(@Param("uuid")String uuid);

}
