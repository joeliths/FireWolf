package com.example.demo.repositories;

import com.example.demo.entities.InventoryProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryProductRepository extends JpaRepository<InventoryProduct,Long> {

}
