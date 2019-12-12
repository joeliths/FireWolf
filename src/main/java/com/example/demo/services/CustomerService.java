package com.example.demo.services;

import com.example.demo.entities.Customer;
import com.example.demo.models.user.Customer.CustomerModel;
import com.example.demo.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    final private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers (){
        return customerRepository.findAll();
    }

    public void addCustomer(CustomerModel customerModel){


    }

    public Customer getCustomerByUuid(String UUID){
        System.out.println(UUID);
        return customerRepository.findByUuid(UUID).orElseThrow(() -> new RuntimeException("WHAAT"));
    }
}
