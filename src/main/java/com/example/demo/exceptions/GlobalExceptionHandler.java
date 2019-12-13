package com.example.demo.exceptions;

import com.example.demo.exceptions.customExceptions.ModelMapperException;
import com.example.demo.exceptions.customExceptions.UserRoleTypeNotFoundException;
import com.example.demo.exceptions.customExceptions.WrongOwnerException;
import com.example.demo.jms.ActiveMQSender;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.validation.ValidationException;
import javax.ws.rs.NotFoundException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ActiveMQSender jms;

    public GlobalExceptionHandler(ActiveMQSender jms) {
        this.jms = jms;
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        //maybe send to jms first here
        return createErrorResponse(BAD_REQUEST, "Request body is missing");
    }

    @ExceptionHandler({ValidationException.class, WrongOwnerException.class})
    public ResponseEntity<?> handleValidationException(Exception e) {
        return createErrorResponse(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({UserRoleTypeNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundException(Exception e){
        return createErrorResponse(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(ModelMapperException.class)
    public ResponseEntity<?> handleModelMapperException(Exception e) {
        return createErrorResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    private ResponseEntity<?> createErrorResponse(HttpStatus httpStatus, String detailedMessage) {
        return ResponseEntity.status(httpStatus)
                .body(new Error(httpStatus.value(), httpStatus.getReasonPhrase(), detailedMessage));
    }


}
