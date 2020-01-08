package com.example.demo.controllers;

import com.example.demo.models.InventoryProductModel;
import com.example.demo.services.StoreService;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    VendorService vendorService;

    @Autowired
    StoreService storeService;


    @GetMapping("{uuid}")
    public ResponseEntity<?> getStoreByUuid(@PathVariable String uuid, Principal principal){
        return ResponseEntity.ok(storeService.getStoreByUuid(uuid, principal.getName()));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<?> getAllStoresToMap(){
        System.out.println("fggg");
        return  new ResponseEntity<>(storeService.getAllStoresToMap(),HttpStatus.OK);
    }

   @GetMapping("/details/{uuid}")
    @ResponseBody
    public ResponseEntity<?> getStoreDetailsByUuid(@PathVariable String uuid){
        return  new ResponseEntity<>(storeService.getStoreDetailsByUuid(uuid),HttpStatus.OK);
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity deleteStore(@PathVariable String uuid, Principal principal){
        storeService.deleteStore(uuid, principal.getName());
        return ResponseEntity.status(204).build();
    }

}
