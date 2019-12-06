package com.example.demo.controllers;

import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {
    @Autowired
    UserService userService;

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
}
