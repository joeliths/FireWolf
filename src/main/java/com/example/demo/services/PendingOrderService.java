package com.example.demo.services;

import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.Store;
import com.example.demo.entities.User;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.StoreModel;
import com.example.demo.models.UserModel;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.repositories.PendingOrderRepository;
import com.example.demo.repositories.StoreRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.validation.ValidationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import java.util.*;
import java.util.stream.Stream;

@Service
public class PendingOrderService {

    private final PendingOrderRepository pendingOrderRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;



    @Autowired
    public PendingOrderService(PendingOrderRepository pendingOrderRepository, ModelMapper modelMapper,
                               UserRepository userRepository, StoreRepository storeRepository) {
        this.pendingOrderRepository = pendingOrderRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
    }

    public void addPendingOrder(PendingOrderRequestModel pendingOrder){

        boolean areFieldsAreMissing = Arrays
                .stream(pendingOrder.getClass().getDeclaredFields()).anyMatch(Objects::isNull);
        if(areFieldsAreMissing) {
            throw new ValidationException("Fields are missing");
        }

        User customer = userRepository
                .findByUserName(pendingOrder.getCustomerUserName())
                .orElseThrow(() -> new ValidationException("Fields are missing"));

        Store store = storeRepository
                .findByUuid(pendingOrder.getStoreUUID())
                .orElseThrow(() -> new ValidationException("Fields are missing"));
        //nothing is null
        //available stock
        //existing user, store, products
        //store actually own these products


    }

    private List<String> validatePendingOrder(PendingOrderRequestModel pendingOrder) {
        List<String>
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


    public void updatePendingOrder(MyUUID pendingOrderUUID, PendingOrderRequestModel newPendingOrder) {
    }






}
