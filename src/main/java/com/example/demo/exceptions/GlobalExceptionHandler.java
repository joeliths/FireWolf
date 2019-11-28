package com.example.demo.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    //Exempel metod för att visa hur det fungerar. Ta bort
    @ExceptionHandler(EntityNotFoundException.class)
    public final ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException e) {
        return responseBuilder(NOT_FOUND, "Some error message for this type of error");
    }

    //Exempel metod för att visa hur det fungerar. Ta bort
    @ExceptionHandler({ValidationException.class, NullPointerException.class})
    public final ResponseEntity<?> anotherExample(Exception e) {
        return responseBuilder(BAD_REQUEST, e.getMessage());
    }

    public ResponseEntity<?> responseBuilder(HttpStatus status, String body) {
        return ResponseEntity.status(status).body(body); //returnera error objekt i body istället?
    }

}
