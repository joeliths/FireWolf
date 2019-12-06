package com.example.demo.controllers;

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
    public ResponseEntity<List<UserResponseModel>> getAllUsers() {
        return ResponseEntity.status(OK).body(userService.getAllUsers());
    }

    @GetMapping("/uuid/{uuid}")
    public ResponseEntity<UserResponseModel> getUserByUuid(@PathVariable String uuid) {
        return ResponseEntity.status(OK).body(userService.getUserByUuid(uuid));
    }

    @GetMapping("/user-name/{userName}")
    public ResponseEntity<UserResponseModel> getUserByUserName(@PathVariable String userName) {
        return ResponseEntity.status(OK).body(userService.getUserByUserName(userName));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseModel> registerUser(@RequestBody UserRegisterModel userModel) {
        return ResponseEntity.status(CREATED).body(userService.registerUser(userModel));
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<UserResponseModel> patchUser(@PathVariable String uuid,
                                                       @RequestBody UserRegisterModel updatedUser) {
        return ResponseEntity.status(OK).body(userService.patchUser(uuid, updatedUser));
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Integer> deleteUserByUuid(@PathVariable String uuid) {
        userService.deleteUserByUUID(uuid);
        return ResponseEntity.status(OK).build();
    }

}
