package com.example.demo.controllers;

import com.example.demo.models.user.UserRegisterModel;
import com.example.demo.models.user.UserResponseModel;
import com.example.demo.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PatchMapping
    public ResponseEntity<UserResponseModel> patchUser(Principal userMakingTheRequest,
                                                       @RequestBody UserRegisterModel updatedUser) {
        userService.patchUser(userMakingTheRequest.getName(), updatedUser);
        return ResponseEntity.status(OK).build();
    }

    @DeleteMapping
    public ResponseEntity<Integer> deleteUser(Principal userMakingTheRequest) {
        userService.deleteUser(userMakingTheRequest.getName());
        return ResponseEntity.status(OK).build();
    }

}
