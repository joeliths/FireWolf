package com.example.demo.services;

import com.example.demo.models.view.PendingOrderProductView;
import com.example.demo.repositories.PendingOrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class PendingOrderProductService {

    final private PendingOrderProductRepository pendingOrderProductRepository;

    @Autowired
    public PendingOrderProductService(PendingOrderProductRepository pendingOrderProductRepository) {
        this.pendingOrderProductRepository = pendingOrderProductRepository;
    }

    public Set<PendingOrderProductView> findByPendingOrderUuid(String uuid){
        return pendingOrderProductRepository.getPendingOrderProductsByPendingOrderUuid(uuid);
    }


}
