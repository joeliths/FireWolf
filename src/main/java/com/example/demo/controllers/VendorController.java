package com.example.demo.controllers;

import com.example.demo.models.StoreModel;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.UUID;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/vendor")
public class VendorController {

    private final VendorService vendorService;

    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUserAsVendor(Principal userMakingTheRequest){
        vendorService.registerUserAsVendor(userMakingTheRequest.getName());
        return ResponseEntity.status(CREATED).build();
    }


    @PostMapping("/register/store")
    public ResponseEntity<?> registerStore(Principal userMakingTheRequest, @RequestBody StoreModel storeModel){
        vendorService.registerStore(userMakingTheRequest.getName(), storeModel);
        return ResponseEntity.status(CREATED).build();
    }


}
