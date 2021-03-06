package com.example.demo.controllers;

import com.example.demo.models.StoreMapModel;
import com.example.demo.models.StoreModel;
import com.example.demo.models.view.StoreCustomerView;
import com.example.demo.services.StoreService;
import com.example.demo.services.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/store")
public class StoreController {

    @Autowired
    VendorService vendorService;

    @Autowired
    StoreService storeService;

    @GetMapping("{uuid}")
    public ResponseEntity<StoreModel> getStoreByUuid(@PathVariable String uuid, Principal principal){
        return ResponseEntity.ok(storeService.getStoreByUuid(uuid, principal.getName()));
    }

    @GetMapping
    @ResponseBody
    public ResponseEntity<Set<StoreMapModel>> getAllStoresToMap(){
        return  new ResponseEntity<>(storeService.getAllStoresToMap(),HttpStatus.OK);
    }

    @GetMapping("/details/{uuid}")
    @ResponseBody
    public ResponseEntity<List<StoreCustomerView>> getStoreDetailsByUuid(@PathVariable String uuid){
        return ResponseEntity.ok(storeService.getStoreDetailsByUuid(uuid));
    }

    @DeleteMapping("{uuid}")
    public ResponseEntity deleteStore(@PathVariable String uuid, Principal principal){
        storeService.deleteStore(uuid, principal.getName());
        return ResponseEntity.status(204).build();
    }

}
