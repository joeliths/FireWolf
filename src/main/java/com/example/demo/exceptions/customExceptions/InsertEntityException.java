package com.example.demo.exceptions.customExceptions;

public class InsertEntityException extends RuntimeException {
    public InsertEntityException(String message) {
        super(message);
    }
}
