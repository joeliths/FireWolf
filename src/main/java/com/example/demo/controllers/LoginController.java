package com.example.demo.controllers;

import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.services.PendingOrderService;
import com.example.demo.services.ProductService;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

    @Autowired
    PendingOrderService pendingOrderService;

    @PostMapping(path = "/login",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> login(@RequestBody String CHANGEME){
        return new ResponseEntity<>( HttpStatus.FAILED_DEPENDENCY);
    }
    @PostMapping(path = "/registerUser",
            consumes = "application/json",
            produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterModel userRegisterModel){
        userService.registerUser(userRegisterModel);
        return new ResponseEntity<>( HttpStatus.OK);
    }

    @GetMapping("/T/out")
    public String logout(){
        SecurityContextHolder.clearContext();
        return "ok";
    }

    @GetMapping("/test/{uuid}")
    public ResponseEntity test(@PathVariable String uuid){

//        //System.out.println( userService.findById(1).getFullName());
//        productService.findByPendingOrderUuid("c0e9db00-1cc5-11ea-b189-1062e58facf1").forEach(p -> {
//            System.out.println("desc: "+p.getDescription() + " \nname:" + p.getName() + "\nQ:" + p.getQuantity());
//        });
        return ResponseEntity.ok(pendingOrderService.getPendingOrderByUuid(uuid));
    }

}
