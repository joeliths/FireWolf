package com.example.demo.services;

import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.repositories.PendingOrderRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class PendingOrderService {

    final private PendingOrderRepository pendingOrderRepository;
    final private ModelMapper modelMapper;

    @Autowired
    public PendingOrderService(PendingOrderRepository pendingOrderRepository, ModelMapper modelMapper) {
        this.pendingOrderRepository = pendingOrderRepository;
        this.modelMapper = modelMapper;
    }

    public void addPendingOrder(PendingOrderRequestModel pendingOrder){
    }

    public List<PendingOrderResponseModel> getPendingOrdersForCustomer(String userName) {
        return Collections.emptyList();
    }

    public List<PendingOrderResponseModel> getPendingOrdersForStore(MyUUID storeUUID) {
        return Collections.emptyList();
    }

    public void getPendingOrders() {

    }


    public void checkoutPendingOrder(MyUUID pendingOrderUUID) {
    }


    public void deletePendingOrder(MyUUID pendingOrderUUID) {
    }


    public void updatePendingOrder(PendingOrderRequestModel pendingOrder) {
    }


}
