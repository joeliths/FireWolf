package com.example.demo.repositories.jpaRepositories;

import com.example.demo.entities.PendingOrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPendingOrderProductRepository extends JpaRepository<PendingOrderProduct, Long> {
}
