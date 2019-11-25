package com.example.demo.services;

import com.example.demo.repositories.PendingOrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class PendingOrderProductService {

    @Autowired
    PendingOrderProductRepository pendingOrderProductRepository;
}
