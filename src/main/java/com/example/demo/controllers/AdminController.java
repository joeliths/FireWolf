package com.example.demo.controllers;

import com.example.demo.models.ProductModel;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @DeleteMapping(path = "/deleteProduct/{uuid}",
             consumes = "application/json",
             produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> deleteProduct(@PathVariable String uuid){
        boolean productDeleted = productService.deleteProduct(uuid);
        return new ResponseEntity<>(productDeleted, HttpStatus.OK);
    }

//    @DeleteMapping(path = "/deleteUser",
//            consumes = "application/json",
//            produces = "application/json")
//    @ResponseBody
//    public ResponseEntity<?> deleteUser(@RequestBody UserModel userModel){
//        boolean userDeleted = userService.deleteUser(userModel);
//        return new ResponseEntity<>(userDeleted, HttpStatus.OK);
//    }

}
