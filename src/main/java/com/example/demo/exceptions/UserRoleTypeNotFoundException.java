package com.example.demo.exceptions;

public class UserRoleTypeNotFoundException extends RuntimeException {
    public UserRoleTypeNotFoundException(String message) {
        super(message);
    }
}
