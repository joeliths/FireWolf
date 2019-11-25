package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {



    Product findByNameLike(@Param("name")String name);


    @Modifying
    @Query("update product p set p.name = :name where p.name = :newName")
    int setNameFor(@Param("name")String name,
                             @Param("newName")String newName);

    @Modifying
    @Query("update description d set d.name = :name where d.name = :newName")
    int setDescriptionFor(@Param("name")String name,
                             @Param("newDescription")String newDescription);




}
