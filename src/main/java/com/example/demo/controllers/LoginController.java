package com.example.demo.controllers;

import com.example.demo.models.user.UserLoginModel;
import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.models.user.UserResponseModel;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public void login(@RequestBody UserLoginModel user){
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseModel> registerUserAndCustomer(@RequestBody UserRegisterModel userModel) {
        userService.registerUserAndCustomer(userModel);
        return ResponseEntity.status(CREATED).build();
    }

    @GetMapping("/logout2")
    public void logout(){
        System.out.println("Damn");
        SecurityContextHolder.clearContext();
    }

}
