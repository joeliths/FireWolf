package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.models.UserModel;
import com.example.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

//    @PostMapping("/register/")
//    public ResponseEntity<UserModel> register(@RequestBody UserModel userModel){
//        User user = new User();
//        user.setUserName(userModel.getUserName());
//        String encryptedPass = passwordEncoder.encode(userModel.getPassword());
//        user.setPassword(encryptedPass);
//        user.setFullName(userModel.getFullName());
//        userRepository.save(user);
//        return ResponseEntity.ok(userModel);
//    }

    @PostMapping("/authenticate")
    public ResponseEntity<?> login(){
        return ResponseEntity.ok("Logged in");
    }

    //TODO: Remove these later. Remove entire class?
    @GetMapping("/test")
    public String success(){
        return "Tried authentication";
    }

    @GetMapping("/vendor")
    public String vendor(){ return "Only for vendors ";}

    @GetMapping("/customers")
    public String customer(){ return "Only for customers";}

}
