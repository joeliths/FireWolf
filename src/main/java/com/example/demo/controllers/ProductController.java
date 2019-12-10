package com.example.demo.controllers;


import com.example.demo.models.ProductModel;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping(path = "/add",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> addProductNewerWay(@RequestBody ProductModel productModel){
        String uuid = productService.addProduct(productModel);
        return new ResponseEntity<>(uuid, HttpStatus.OK);
    }

    @GetMapping(path ="/getByName",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> GetProductsByName(@RequestParam(value = "name", required = true) String name){
        Set<ProductModel> results = productService.getProductsLike(name);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

}
