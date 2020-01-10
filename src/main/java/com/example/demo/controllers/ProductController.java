package com.example.demo.controllers;

import com.example.demo.models.ProductModel;
import com.example.demo.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.QueryParam;
import java.util.Set;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping(
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
    public ResponseEntity<Set<ProductModel>> GetProductsByName(@QueryParam(value = "name") String name){
        Set<ProductModel> results = productService.getProductsLike(name);
        return new ResponseEntity<>(results, HttpStatus.OK);
    }

    @GetMapping(path ="/{uuid}",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<ProductModel> GetProductsByUuid(@PathVariable(value = "uuid", required = true) String uuid){
        ProductModel resultModel= productService.getProductByUuid(uuid);
        return new ResponseEntity<>(resultModel, HttpStatus.OK);
    }

    @PatchMapping(path = "/{uuid}",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity updateProductByUuid(@PathVariable(value = "uuid", required = true)String uuid,
                                              @RequestBody ProductModel productModel){
        productService.updateProduct(uuid,productModel);
        return new ResponseEntity(HttpStatus.OK);
    }

}
