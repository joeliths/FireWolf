package com.example.demo.exceptions;

import com.example.demo.jms.ActiveMQSender;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ActiveMQSender jms;

    public GlobalExceptionHandler(ActiveMQSender jms) {
        this.jms = jms;
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<?> handleValidationException(ValidationException e) {
        //jms.sendExceptionDetailsToExceptionQueue(e);
        return ResponseEntity.status(BAD_REQUEST).body(e.getMessage());
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public ResponseEntity<?> handleValidationException(HttpMessageNotReadableException e) {
//        //jms.sendExceptionDetailsToExceptionQueue(e);
//        return ResponseEntity.status(BAD_REQUEST).body(e); missing request body
//    }


//    //TODO: Remove when we think we have handled all exceptions that can occur. :) Replace with some "last resort" exception handler
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<?> handleUnanticipatedException(Exception e) {
//        return ResponseEntity.status(I_AM_A_TEAPOT)
//                .body("'" + e.getClass() + "' is not added in our exception handler yet.\n Stacktrace: " +
//                        Arrays.toString(e.getStackTrace()));
//    }

}
