package com.example.demo.services;

import com.example.demo.repositories.IPendingOrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PendingOrderProductService {

    @Autowired
    IPendingOrderProductRepository iPendingOrderProductRepository;
}
