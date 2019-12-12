package com.example.demo.controllers;

import com.example.demo.models.StoreModel;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/register-vendor/{userUuid}")
    public ResponseEntity<?> registerUserAsVendor(@PathVariable String userUuid){
        vendorService.registerUserAsVendor(userUuid);
        return ResponseEntity.status(CREATED).build();
    }


    @PostMapping("/register-store/{vendorUuid}")
    public ResponseEntity<?> registerStore(@PathVariable String vendorUuid, @RequestBody StoreModel storeModel){
        vendorService.registerStore(vendorUuid, storeModel);
        return ResponseEntity.status(CREATED).build();
    }


}
