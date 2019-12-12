package com.example.demo.controllers;


import com.example.demo.entities.Product;
import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;


    //TODO: Check if working
    @PostMapping(path = "/add",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProductNewerWay(@RequestBody ProductModel productModel){
        String uuid = productService.addProduct(productModel);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }
    //TODO: Check if working
    @GetMapping(path ="/getByName",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> GetProductsByName(@PathParam(value = "name") String name){
        Set<ProductModel> results = productService.getProductsLike(name);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }
    //TODO: Check if working
    @GetMapping(path ="/getByUuid",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> GetProductsByUuid(@RequestParam(value = "uuid", required = true) String uuid){
        Set<ProductModel> results = productService.getProductsLike(uuid);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
