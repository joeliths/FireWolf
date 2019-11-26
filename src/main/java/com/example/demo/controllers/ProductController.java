package com.example.demo.controllers;


import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(path = "/products/add",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProductNewerWay(@RequestBody ProductModel productModel){
        String uuid = productService.addProduct(productModel);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }

}
