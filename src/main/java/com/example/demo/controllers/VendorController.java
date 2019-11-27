package com.example.demo.controllers;

import com.example.demo.models.StoreModel;
import com.example.demo.models.VendorModel;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/vendor")
public class VendorController {
    @Autowired
    VendorService vendorService;

    @PostMapping(path = "/registerVendor",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> registerVendor(@RequestBody VendorModel vendorModel){
        String uuid = vendorService.registerVendor(vendorModel);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
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
