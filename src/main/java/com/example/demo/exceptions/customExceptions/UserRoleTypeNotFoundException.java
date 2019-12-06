package com.example.demo.exceptions.customExceptions;

public class UserRoleTypeNotFoundException extends RuntimeException {
    public UserRoleTypeNotFoundException(String message) {
        super(message);
    }
}
