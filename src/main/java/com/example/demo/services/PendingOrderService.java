package com.example.demo.services;

import com.example.demo.entities.InventoryProduct;
import com.example.demo.entities.PendingOrder;
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

    public void getPendingOrders() {

        PendingOrder order = pendingOrderRepository.findAll().get(0);
        PendingOrderResponseModel p = new PendingOrderResponseModel();
        //p.setNonNestedField(order.theNonNestedField)
        //p.setStore(new NestedStoreModel(fields here));

        /*
        private String placemenDateTime;
        private String expirationDateTime;
        private StoreModel store;
        private CustomerModel customer;
        private List<PendingOrderProductResponseModel> orderedProducts;
        */


    }

    public void addPendingOrder(PendingOrderRequestModel pendingOrder){



//        if(!isOrderValid(pendingOrder)) {
//            throw new ValidationException("Some error here...");
//        }

    }

    private boolean isOrderValid(PendingOrderRequestModel pendingOrder) {

        boolean noFieldsAreMissing = Arrays
                .stream(pendingOrder.getClass().getDeclaredFields()).allMatch(Objects::nonNull);

        boolean customerExists = true;
        boolean storeExists = true;
        boolean productsBelongToSelectedStore = true;
        boolean productsAreInStock = true;

        return true;
    }



//    private boolean isOrderValid(PendingOrderRequestModel pendingOrder) {
//        Optional<User> customer = userRepository.findByUserName(pendingOrder.getCustomerUserName());
//        Optional<Store> store = storeRepository.findByUuid(pendingOrder.getStoreUUID());
//
//        boolean noFieldsAreMissing = Arrays
//                .stream(pendingOrder.getClass().getDeclaredFields()).allMatch(Objects::nonNull);
//
//        boolean customerExists = customer.isPresent();
//        boolean storeExists = store.isPresent();
//        boolean productsBelongToSelectedStore = true;
//        boolean productsAreInStock = true;
//
//        return noFieldsAreMissing;
//    }


    public List<PendingOrderResponseModel> getPendingOrdersForCustomer(String userName) {
        return Collections.emptyList();
    }

    public List<PendingOrderResponseModel> getPendingOrdersForStore(MyUUID storeUUID) {
        return Collections.emptyList();
    }




    public void checkoutPendingOrder(MyUUID pendingOrderUUID) {
    }


    public void deletePendingOrder(MyUUID pendingOrderUUID) {
    }


    public void updatePendingOrder(MyUUID pendingOrderUUID, PendingOrderRequestModel newPendingOrder) {
    }




}
