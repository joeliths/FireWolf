package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.*;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.pendingorder.PendingOrderModel;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.models.view.PendingOrderProductView;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.*;

@Service
public class PendingOrderService {

    private final PendingOrderRepository pendingOrderRepository;
    private final PendingOrderProductRepository pendingOrderProductRepository;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    Convert convert;

    @Autowired
    public PendingOrderService(PendingOrderRepository pendingOrderRepository,
                               PendingOrderProductRepository pendingOrderProductRepository,
                               ProductRepository productRepository
                               ) {
        this.pendingOrderRepository = pendingOrderRepository;
        this.pendingOrderProductRepository = pendingOrderProductRepository;
    }

    public List<PendingOrderModel> getPendingOrdersByCustomerUuid(String customerUuid) {
          //TODO: check uuid belongs to logged in user

            return null;
    }

    public PendingOrderResponseModel getPendingOrderByUuid(String uuid){
        Optional<PendingOrder> optionalPendingOrder = pendingOrderRepository.findByUuid(uuid);

        if(optionalPendingOrder.isPresent()){
            PendingOrder pendingOrder = optionalPendingOrder.get();
            PendingOrderResponseModel pendingOrderModel = convert.lowAccessConverter(pendingOrder, PendingOrderResponseModel.class);

            Set<PendingOrderProductView> products = getPendingOrderViewsByPendingOrderUuid(uuid);
            pendingOrderModel.setPendingOrderProductsViews(products);
            return pendingOrderModel;
        }else
            throw new EntityNotFoundException("Pending order with uuid "+uuid+" cannot be found");

    }

    public Set<PendingOrderProductView> getPendingOrderViewsByPendingOrderUuid(String pendingOrderUuid){
        return pendingOrderProductRepository.getPendingOrderProductsByPendingOrderUuid(pendingOrderUuid);
    }


    public String addPendingOrder(PendingOrderRequestModel pendingOrderModel){
     int insertedRows = pendingOrderRepository.insertPendingOrder(new Date(), new Date(),
                                                                    pendingOrderModel.getCustomerUUID(),
                                                                    pendingOrderModel.getStoreUUID());
     PendingOrder pendingOrder;
     if(insertedRows > 0){
         pendingOrder = pendingOrderRepository.getLatestPendingOrder();
     }else
         throw new RuntimeException();

     pendingOrderModel.getOrderedProducts().forEach(p -> {
         pendingOrderProductRepository.insertPendingOrderProduct(p.getQuantity(), p.getInventoryProductUUID(), pendingOrder.getUuid().toString());
     });

     List<PendingOrderProduct> products = pendingOrderProductRepository.getPendingOrderProductByPendingOrderUuid(pendingOrder.getUuid().toString());


     return "";

    }

    public void deletePendingOrder(String uuid){
        PendingOrder pendingOrder = pendingOrderRepository.findByUuid(uuid).get();
        pendingOrderRepository.delete(pendingOrder);
    }


    public void checkoutPendingOrder(MyUUID pendingOrderUUID) {
    }


    public void updatePendingOrder(MyUUID pendingOrderUUID, PendingOrderRequestModel newPendingOrder) {
    }




}
