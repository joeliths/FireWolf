package com.example.demo.controllers;

import com.example.demo.models.user.UserModel;
import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.models.user.UserResponseModel;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<UserModel>> getAllUsers() {
        return ResponseEntity.status(OK).body(userService.getAllUsers());
    }

    @GetMapping("/{uuid}")
    public ResponseEntity<UserModel> getUserByUuid(@PathVariable String uuid) {
        return ResponseEntity.status(OK).body(userService.findUserByUuid(uuid));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseModel> registerUser(@RequestBody UserRegisterModel userModel) {
        return ResponseEntity.status(CREATED).body(userService.registerUser(userModel));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Boolean> deleteUserByUuid(@PathVariable String uuid) {
        return ResponseEntity.status(OK).body(userService.deleteUserByUUID(uuid));
    }

    //TODO. Update/patch user endpoint.

}
