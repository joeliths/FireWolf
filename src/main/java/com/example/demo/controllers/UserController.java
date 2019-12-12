package com.example.demo.controllers;

import com.example.demo.models.user.UserRequestModel;
import com.example.demo.models.user.UserResponseModel;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponseModel> registerUserAndCustomer(@RequestBody UserRequestModel userModel) {
        userService.registerUserAndCustomer(userModel);
        return ResponseEntity.status(CREATED).build();
    }

    @PatchMapping("/{uuid}")
    public ResponseEntity<UserResponseModel> patchUser(@PathVariable String uuid,
                                                       @RequestBody UserRequestModel updatedUser) {
        userService.patchUser(uuid, updatedUser);
        return ResponseEntity.status(OK).build();
    }

    @DeleteMapping("/{uuid}")
    public ResponseEntity<Integer> deleteUserByUuid(@PathVariable String uuid) {
        userService.deleteUserByUUID(uuid);
        return ResponseEntity.status(OK).build();
    }

}
