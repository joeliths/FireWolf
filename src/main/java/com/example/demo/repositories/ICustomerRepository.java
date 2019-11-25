package com.example.demo.repositories;

import com.example.demo.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICustomerRepository extends JpaRepository<Customer,Long> {


}
