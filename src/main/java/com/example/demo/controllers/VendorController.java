package com.example.demo.controllers;

import com.example.demo.models.StoreModel;
import com.example.demo.models.VendorModel;
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

    @PostMapping("/register/{userUuid}")
    public ResponseEntity<Boolean> registerUserAsVendor(@PathVariable String userUuid){
        return ResponseEntity.status(CREATED).body(vendorService.registerUserAsVendor(userUuid));
    }


    @PostMapping(path = "/registerStore",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> registerStore(@RequestBody StoreModel storeModel){
        String uuid = vendorService.registerStore(storeModel);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }


}
