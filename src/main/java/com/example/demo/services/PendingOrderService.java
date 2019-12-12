package com.example.demo.services;

import com.example.demo.Mapper.Convert;
import com.example.demo.entities.*;
import com.example.demo.entities.helperclasses.MyUUID;
import com.example.demo.models.pendingorder.PendingOrderRequestModel;
import com.example.demo.models.pendingorder.PendingOrderResponseModel;
import com.example.demo.models.pendingorder.PendingOrderWithView;
import com.example.demo.models.view.PendingOrderProductView;
import com.example.demo.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PendingOrderService {

    private final PendingOrderRepository pendingOrderRepository;
    private final UserRepository userRepository;
    private final StoreRepository storeRepository;
    private final InventoryProductRepository inventoryProductRepository;
    private final PendingOrderProductRepository pendingOrderProductRepository;
    private final ProductRepository productRepository;
    private final CustomerService customerService;

    @Autowired
    VendorRepository vendorRepository;

    @Autowired
    Convert convert;

    @Autowired
    public PendingOrderService(PendingOrderRepository pendingOrderRepository,
                               UserRepository userRepository, StoreRepository storeRepository,
                               InventoryProductRepository inventoryProductRepository,
                               PendingOrderProductRepository pendingOrderProductRepository,
                               ProductRepository productRepository,
                               CustomerService customerService) {
        this.pendingOrderRepository = pendingOrderRepository;
        this.userRepository = userRepository;
        this.storeRepository = storeRepository;
        this.inventoryProductRepository = inventoryProductRepository;
        this.pendingOrderProductRepository = pendingOrderProductRepository;
        this.productRepository = productRepository;
        this.customerService = customerService;
    }

    public void getPendingOrders() {

    }

    public PendingOrderWithView getPendingOrderByUuid(String uuid){
        PendingOrder pendingOrder = pendingOrderRepository.findByUuid(uuid).get();
        System.out.println(pendingOrder.getCustomer());
        System.out.println(pendingOrder.getCustomer().getId());
        PendingOrderWithView pendingOrderModel = convert.lowAccessConverter(pendingOrder, PendingOrderWithView.class);
        pendingOrderModel.getCustomer();


//        CustomerModel customerModel;
//        StoreModel storeModel;
        Set<PendingOrderProductView> view = pendingOrderProductRepository.getPendingOrderProductsByPendingOrderUuid(uuid);
        System.out.println("woah");
        view.forEach(p -> {
            System.out.println("damn");
            System.out.println(p.getDescription() + " " + p.getName() + " " + p.getPrice() + " " + p.getQuantity());
        });
        pendingOrderModel.setPendingOrderProductsViews(view);
        return pendingOrderModel;
    }

    public PendingOrderResponseModel findPendingOrderByUuid(String uuid) {
        return null;
    }

    public String addPendingOrder(PendingOrderRequestModel pendingOrderModel){
     int insertedRows = pendingOrderRepository.insertPendingOrder(new Date(), new Date(), pendingOrderModel.getCustomerUUID(), pendingOrderModel.getStoreUUID());
     PendingOrder pendingOrder;
     if(insertedRows > 0){
         pendingOrder = pendingOrderRepository.getLatestPendingOrderUuid();
     }else
         throw new RuntimeException();

     pendingOrderModel.getOrderedProducts().forEach(p -> {
         pendingOrderProductRepository.insertPendingOrderProduct(p.getQuantity(), p.getInventoryProductUUID(), pendingOrder.getUuid().toString());
     });

     List<PendingOrderProduct> products = pendingOrderProductRepository.getPendingOrderProductByPendingOrderUuid(pendingOrder.getUuid().toString());


     products.forEach(System.out::println);

     return "";


//        Set<PendingOrderProduct> products = pendingOrder.getOrderedProducts().stream()
//                .map(p -> {
//                    PendingOrderProduct product = new PendingOrderProduct();
//                    product.setPendingOrder();
//                })
//        System.out.println(order2.getId());
//        Store store = storeRepository.findByUuid(pendingOrder.getStoreUUID()).get();
//        order.setStore(store);
//        System.out.println(pendingOrder.getCustomerUUID());
//        Customer customer = customerService.getCustomerByUuid(pendingOrder.getCustomerUUID());
//        order.setCustomer(customer);
//        pendingOrderRepository.save(order);
//
//        List<PendingOrderProductResponseModel> pendingOrderProductResponseModels = new ArrayList<>();
//        Set<PendingOrderProduct> products = new HashSet<>();
//        pendingOrder.getOrderedProducts().forEach(p -> {
//            InventoryProduct inventoryProduct = inventoryProductRepository.findByUuid(p.getInventoryProductUUID().toString()).get();
//
//            inventoryProduct.getProduct().getName();
//            inventoryProduct.getProduct().getDescription();
//
//            PendingOrderProduct product = new PendingOrderProduct(p.getQuantity());
//            product.setInventoryProduct(inventoryProduct);
//            product.setPendingOrder(order);
//
//            inventoryProduct.setStock(inventoryProduct.getStock() - 1);
//
//            pendingOrderProductRepository.save(product);
//            inventoryProductRepository.save(inventoryProduct);
//
//            products.add(product);
//            PendingOrderProductResponseModel pendingOrderProductResponseModel = new PendingOrderProductResponseModel();
//            pendingOrderProductResponseModel.setName(inventoryProduct.getProduct().getName());
//            pendingOrderProductResponseModel.setQuantity(product.getQuantity());
//            pendingOrderProductResponseModels.add(pendingOrderProductResponseModel);
//        });
//
//        StoreModel storeModel = new StoreModel();
//        storeModel.setAddress(store.getAddress());
//        storeModel.setDescription(store.getDescription());
//
//        CustomerModel customerModel = new CustomerModel();
//        PendingOrderResponseModel pendingOrderResponseModel = new PendingOrderResponseModel(order.getUuid().toString(), order.getPlacemenDateTime().toString(), order.getExpirationDateTime().toString(), storeModel, customerModel, pendingOrderProductResponseModels);
//        customerModel.setPendingOrders(new HashSet<>(Arrays.asList(pendingOrderResponseModel.getUUID())));
//        return new PendingOrderResponseModel();
    }

    public void deletePendingOrder(String uuid){
        PendingOrder pendingOrder = pendingOrderRepository.findByUuid(uuid).get();
        pendingOrderRepository.delete(pendingOrder);
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
