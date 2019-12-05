package com.example.demo.exceptions;

import com.example.demo.exceptions.customExceptions.UserNotFoundException;
import com.example.demo.exceptions.customExceptions.UserRoleTypeNotFoundException;
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

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        //maybe send to jms first here
        return createErrorResponse(BAD_REQUEST, "Request body is missing");
    }

    @ExceptionHandler({UserRoleTypeNotFoundException.class,
            UserNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundException(Exception e){
        return createErrorResponse(NOT_FOUND, "Entity not found");
    }

    private ResponseEntity<?> createErrorResponse(HttpStatus httpStatus, String detailedMessage) {
        return ResponseEntity.status(httpStatus)
                .body(new Error(httpStatus.value(), httpStatus.getReasonPhrase(), detailedMessage));
    }


}
