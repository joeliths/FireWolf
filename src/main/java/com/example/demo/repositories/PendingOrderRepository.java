package com.example.demo.repositories;

import com.example.demo.entities.PendingOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface PendingOrderRepository extends JpaRepository<PendingOrder,Long> {

    @Query(nativeQuery = true, value = "select * from pending_order p where p.uuid = :uuid")
    Optional<PendingOrder> findByUuid(@Param("uuid") String uuid);
    
}
