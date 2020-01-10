package com.example.demo.repositories;

import com.example.demo.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.Set;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Set<Product> findByNameIgnoreCaseContaining(@Param("name")String name);

    @Modifying
    @Query("update Product p set p.name = :newName where p.uuid = :uuid")
    int updateName(@Param("uuid")String uuid,
                             @Param("newName")String newName);

    @Modifying
    @Query("update Product p set p.description = :newDescription where p.uuid = :uuid")
    int updateDescription(@Param("uuid")String uuid,
                             @Param("newDescription")String newDescription);

    @Modifying
    @Query(nativeQuery = true,
            value = "update product set uuid = :#{#product.uuid.toString()}, " +
                    "name = :#{#product.name}, " +
                    "description = :#{#product.description} " +
                    "where id = :#{#product.id}")
    void updateProduct(@Param("product") Product product);

    @Query(nativeQuery = true, value = "select * from product where product.uuid = :uuid")
    Optional<Product> findByUuid(@Param("uuid") String uuid);

    @Query(nativeQuery = true, value = "select * from product where product.uuid = :uuid")
    Product getByUuid(@Param("uuid") String uuid);

}
