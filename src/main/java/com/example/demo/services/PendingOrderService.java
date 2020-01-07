package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.*;
import com.example.demo.exceptions.customExceptions.InsertEntityException;
import com.example.demo.exceptions.customExceptions.WrongOwnerException;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.models.view.PendingOrderProductView;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PendingOrderService {

    private final PendingOrderRepository pendingOrderRepository;
    private final PendingOrderProductRepository pendingOrderProductRepository;
    private final CustomerRepository customerRepository;
    private final VendorService vendorService;

    private final UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    Convert convert;

    @Autowired
    public PendingOrderService(PendingOrderRepository pendingOrderRepository,
                               PendingOrderProductRepository pendingOrderProductRepository,
                               UserRepository userRepository,
                               CustomerRepository customerRepository,
                               VendorService vendorService
                               ) {
        this.pendingOrderRepository = pendingOrderRepository;
        this.pendingOrderProductRepository = pendingOrderProductRepository;
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.vendorService = vendorService;
    }

    public PendingOrderResponseModel getPendingOrderByUuid(String uuid, String username){
       PendingOrder pendingOrder = getPendingOrderEntityByUuid(uuid);
       if(customerRepository.findByUserName(username).isPresent()){
           return toResponseModel(pendingOrder);
       }else
           throw new WrongOwnerException("Pending order with uuid "+uuid+" does not belong to user "+username);
    }

    public PendingOrder getPendingOrderEntityByUuid(String uuid){
        Optional<PendingOrder> optionalPendingOrder = pendingOrderRepository.findByUuid(uuid);
        return optionalPendingOrder.orElseThrow(() -> new EntityNotFoundException("Pending order with uuid "+uuid+" cannot be found"));
    }

    public Set<PendingOrderProductView> getPendingOrderViewsByPendingOrderUuid(String pendingOrderUuid){
        return pendingOrderProductRepository.getPendingOrderProductsByPendingOrderUuid(pendingOrderUuid);
    }


    @Transactional
    public String addPendingOrder(PendingOrderRequestModel pendingOrderModel, String username){
     int insertedRows = pendingOrderRepository.insertPendingOrder(
                                                                    username,
                                                                    pendingOrderModel.getStoreUUID());
     PendingOrder pendingOrder;
     if(insertedRows > 0){
         pendingOrder = pendingOrderRepository.getLatestPendingOrder();
     }else
         throw new InsertEntityException("Order failed to be saved");

     String storeUuid = pendingOrderModel.getStoreUUID();

     pendingOrderModel.getPendingOrderProducts().forEach(p -> {
         if(vendorService.doesInventoryProductNotExistInStore(storeUuid, p.getInventoryProductUUID()))
             throw new WrongOwnerException("Store with uuid "+storeUuid+" does not carry inventory product");                                   pendingOrderProductRepository.insertPendingOrderProduct(p.getQuantity(), p.getInventoryProductUUID(), pendingOrder                  .getUuid().toString());
        });

     return pendingOrder.getUuid().toString();

    }

    public void deletePendingOrder(String uuid, String username){
        PendingOrder pendingOrder = getPendingOrderEntityByUuid(uuid);
        if(customerRepository.findByUserName(username).isPresent()){
            pendingOrderRepository.delete(pendingOrder);
        }else
            throw new WrongOwnerException("Pending order with uuid "+uuid+" does not belong to user "+username);
    }

    public void updatePendingOrder(String uuid, PendingOrderRequestModel newPendingOrder, String username) {
        PendingOrder pendingOrder = getPendingOrderEntityByUuid(uuid);
        if(customerRepository.findByUserName(username).isPresent()){
            //TODO: Update pendingorder
        }else
            throw new WrongOwnerException("Pending order with uuid "+uuid+" does not belong to user "+username);
    }


    public List<PendingOrderResponseModel> getPendingOrdersForCustomer(String userName) {
        return pendingOrderRepository.getPendingOrderByCustomer(userName)
                .stream()
                .map(po -> toResponseModel(po)).collect(Collectors.toList());
    }

    public List<PendingOrderResponseModel> getPendingOrdersForStore(String storeUuid, String userName){
        vendorService.doesStoreNotBelongToVendor(userName, storeUuid);
        return pendingOrderRepository.getPendingOrderByStore(storeUuid)
                .stream()
                .map(po -> toResponseModel(po)).collect(Collectors.toList());
    }

    public PendingOrderResponseModel toResponseModel(PendingOrder pendingOrder){
        PendingOrderResponseModel pendingOrderModel = convert.lowAccessConverter(pendingOrder, PendingOrderResponseModel.class);
        Set<PendingOrderProductView> products = getPendingOrderViewsByPendingOrderUuid(pendingOrderModel.getUuid());
        pendingOrderModel.setPendingOrderProductsViews(products);
        return  pendingOrderModel;
    }


}
