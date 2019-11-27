package com.example.demo.services;

import com.example.demo.entities.PendingOrder;
import com.example.demo.models.PendingOrderModel;
import com.example.demo.repositories.PendingOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;

@Service
public class PendingOrderService {

    final private PendingOrderRepository pendingOrderRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Autowired
    public PendingOrderService(PendingOrderRepository pendingOrderRepository) {
        this.pendingOrderRepository = pendingOrderRepository;
    }

    public void addPendingOrder(PendingOrderModel pendingOrderModel){
        PendingOrder pendingOrder = modelMapper.map(pendingOrderModel,PendingOrder.class);
        pendingOrderRepository.saveAndFlush(pendingOrder);

    }

    //Todo:write this method
    public void checkoutPendingOrder(PendingOrderModel pendingOrderModel) {
    }
    //Todo:write this method
    public void updatePendingOrder(PendingOrderModel pendingOrderModel) {
    }
    //TODO:write this method
    public void deletePendingOrder(PendingOrderModel pendingOrderModel) {
    }
}
