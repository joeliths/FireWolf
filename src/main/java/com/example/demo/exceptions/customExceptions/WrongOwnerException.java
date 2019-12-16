package com.example.demo.exceptions.customExceptions;

public class WrongOwnerException extends RuntimeException{

    public WrongOwnerException(String msg){
        super(msg);
    }
}
