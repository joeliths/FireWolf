package com.example.demo.repositories;


import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(nativeQuery = true, value = "select * from customer c where c.uuid = :uuid")
    Optional<Customer> findByUuid(@Param("uuid") String uuid);

}
