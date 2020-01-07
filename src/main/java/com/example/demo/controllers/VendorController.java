package com.example.demo.controllers;

import com.example.demo.models.InventoryProductRequestModel;
import com.example.demo.models.StoreModel;
import com.example.demo.services.PendingOrderService;
import com.example.demo.services.StoreService;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.security.Principal;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;
    private final StoreService storeService;
    private final PendingOrderService pendingOrderService;

    public VendorController(VendorService vendorService, StoreService storeService,
                            PendingOrderService pendingOrderService) {
        this.vendorService = vendorService;
        this.storeService = storeService;
        this.pendingOrderService = pendingOrderService;
    }


    @GetMapping("/store")
    public ResponseEntity<?> getAllStores(Principal principal){
        return ResponseEntity.ok(storeService.getAllStoresByUsername(principal.getName()));
    }

    @PostMapping
    public ResponseEntity<?> registerUserAsVendor(Principal userMakingTheRequest){
        vendorService.registerUserAsVendor(userMakingTheRequest.getName());
        return ResponseEntity.status(CREATED).build();
    }


    @PostMapping("/store/add")
    public ResponseEntity<?> addStore(Principal userMakingTheRequest, @RequestBody StoreModel storeModel){
        vendorService.addStore(userMakingTheRequest.getName(), storeModel);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/store/{uuid}/pending-orders")
    public ResponseEntity<?> getStorePendingOrders(Principal userMakingTheRequest, @PathVariable String uuid){
        return ResponseEntity.ok(pendingOrderService.getPendingOrdersForStore(uuid, userMakingTheRequest.getName()));
    }

    @PostMapping("/product/add/{storeUuid}/{productUuid}")
    public ResponseEntity<?> addExistingTypeOfProductToStore(Principal userMakingTheRequest,
                                                             @PathVariable String storeUuid,
                                                             @PathVariable String productUuid,
                                                             @RequestBody InventoryProductRequestModel product) {
        vendorService.addInventoryProductToStore(userMakingTheRequest.getName(), storeUuid, productUuid, product);
        return ResponseEntity.status(CREATED).build();
    }

    @PatchMapping("/product/update/{storeUuid}/{inventoryProductUuid}")
    public ResponseEntity<?> updateProductInStore(Principal userMakingTheRequest, @PathVariable String storeUuid,
                                                  @PathVariable String inventoryProductUuid,
                                                  @RequestBody InventoryProductRequestModel updatedProduct) {
        vendorService.updateProductInStore(userMakingTheRequest.getName(), storeUuid, inventoryProductUuid,
                updatedProduct);
        return ResponseEntity.status(OK).build();
    }

    @DeleteMapping("/product/remove/{storeUuid}/{inventoryProductUuid}")
    public ResponseEntity<?> removeProductFromStore(Principal userMakingTheRequest, @PathVariable String storeUuid,
                                                    @PathVariable String inventoryProductUuid) {
        vendorService.removeProductFromStore(userMakingTheRequest.getName(), storeUuid, inventoryProductUuid);
        return ResponseEntity.status(OK).build();
    }


}
