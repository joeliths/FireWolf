package com.example.demo.services;

import com.example.demo.repositories.PendingOrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PendingOrderProductService {

    final private PendingOrderProductRepository pendingOrderProductRepository;

    @Autowired
    public PendingOrderProductService(PendingOrderProductRepository pendingOrderProductRepository) {
        this.pendingOrderProductRepository = pendingOrderProductRepository;
    }
}
