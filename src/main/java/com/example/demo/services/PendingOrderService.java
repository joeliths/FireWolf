package com.example.demo.services;

import com.example.demo.entities.*;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.repositories.PendingOrderRepository;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.repositories.VendorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PendingOrderService {

    private final PendingOrderRepository pendingOrderRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;

    @Autowired
    VendorRepository vendorRepository;


    @Autowired
    public PendingOrderService(PendingOrderRepository pendingOrderRepository,
                               UserRepository userRepository, StoreRepository storeRepository) {
        this.pendingOrderRepository = pendingOrderRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public void getPendingOrders() {

    }

    public PendingOrderResponseModel findPendingOrderByUuid(String uuid) {
        return null;
    }

    public PendingOrderResponseModel addPendingOrder(PendingOrderRequestModel pendingOrder){
        return null;
    }



//    public List<PendingOrderResponseModel> getPendingOrdersForCustomer(String userName) {
//        return Collections.emptyList();
//    }
//    public List<PendingOrderResponseModel> getPendingOrdersForStore(String storeUUID) {
//        return Collections.emptyList();
//    }





    public void checkoutPendingOrder(MyUUID pendingOrderUUID) {
    }


    public void deletePendingOrder(MyUUID pendingOrderUUID) {
    }


    public void updatePendingOrder(MyUUID pendingOrderUUID, PendingOrderRequestModel newPendingOrder) {
    }




}
