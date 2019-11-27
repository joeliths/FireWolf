package com.example.demo.controllers;

import com.example.demo.models.InventoryProductModel;
import com.example.demo.services.InventoryProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/store")
public class StoreController {
    @Autowired
    InventoryProductService inventoryProductService;

    @PostMapping(path = "/addProduct",
                 consumes = "application/json",
                 produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProduct(@RequestBody InventoryProductModel inventoryProductModel){
        boolean productAdded = inventoryProductService.addProduct(inventoryProductModel);
        return new ResponseEntity<>(productAdded, HttpStatus.OK);
    }

    @PatchMapping(path = "/patchProduct",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> patchProduct(@RequestBody InventoryProductModel inventoryProductModel){
        boolean productPatched = inventoryProductService.patchProduct(inventoryProductModel);
        return new ResponseEntity<>(productPatched, HttpStatus.OK);
    }

    @DeleteMapping(path = "/deleteProduct",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> deleteProduct(@RequestBody InventoryProductModel inventoryProductModel){
        boolean productDeleted = inventoryProductService.deleteProduct(inventoryProductModel);
        return new ResponseEntity<>(productDeleted, HttpStatus.OK);
    }

}
