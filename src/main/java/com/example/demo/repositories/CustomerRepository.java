package com.example.demo.repositories;


import com.example.demo.entities.Customer;
import com.example.demo.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;


public interface CustomerRepository extends JpaRepository<Customer,Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM customer where user_id = (SELECT id FROM " +
            "user WHERE user.user_name = :userName)")
    Optional<Customer> findByUserName(@Param("userName") String userName);
}
