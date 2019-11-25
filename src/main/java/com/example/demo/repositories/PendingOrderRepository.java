package com.example.demo.repositories;

import com.example.demo.entities.PendingOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PendingOrderRepository extends JpaRepository<PendingOrder,Long> {
    
}
