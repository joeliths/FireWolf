package com.example.demo.exceptions;

import com.example.demo.exceptions.customExceptions.*;
import com.example.demo.jms.ActiveMQSender;
import org.hibernate.JDBCException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ActiveMQSender jms;

    public GlobalExceptionHandler(ActiveMQSender jms) {
        this.jms = jms;
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return createErrorResponse(BAD_REQUEST, "Invalid request body.");
    }

    @ExceptionHandler(NotSufficientStockException.class)
    public ResponseEntity<?> notSufficientStockException(NotSufficientStockException e) {
        return createErrorResponse(BAD_REQUEST, "sufficient stock not available.");
    }

    @ExceptionHandler({ValidationException.class, WrongOwnerException.class, BadRequestException.class})
    public ResponseEntity<?> handleValidationException(Exception e) {
        return createErrorResponse(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<?> handleForbiddenException(ForbiddenException e) {
        return createErrorResponse(FORBIDDEN, e.getMessage());
    }

    @ExceptionHandler({UserRoleTypeNotFoundException.class, EntityNotFoundException.class})
    public ResponseEntity<?> handleEntityNotFoundException(Exception e){
        return createErrorResponse(NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler({JDBCException.class, InsertEntityException.class})
    public ResponseEntity<?> handleSQLException(Exception e){
        return createErrorResponse(BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler(ModelMapperException.class)
    public ResponseEntity<?> handleModelMapperException(Exception e) {
        return createErrorResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler({AuthenticationException.class, BadCredentialsException.class})
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e, HttpServletResponse response){
        return createErrorResponse(UNAUTHORIZED, e.getMessage());
    }

    private ResponseEntity<?> createErrorResponse(HttpStatus httpStatus, String detailedMessage) {
        Error customExceptionDetails = new Error(httpStatus.value(), httpStatus.getReasonPhrase(), detailedMessage);
        jms.sendExceptionDetailsToExceptionQueue(customExceptionDetails);
        return ResponseEntity.status(httpStatus).body(customExceptionDetails);
    }

}
