package com.example.demo.controllers;

import com.example.demo.models.UserModel;
import com.example.demo.services.IUserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(OK).body(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<UserModel> registerUser(@RequestBody UserModel userModel) {
        return ResponseEntity.status(CREATED).body(userService.addUser(userModel));
    }

}
