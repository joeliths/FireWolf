package com.example.demo.exceptions;

import com.example.demo.exceptions.customExceptions.ModelMapperException;
import com.example.demo.exceptions.customExceptions.UserRoleTypeNotFoundException;
import com.example.demo.exceptions.customExceptions.WrongOwnerException;
import com.example.demo.jms.ActiveMQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.HandlerExceptionResolver;

import javax.persistence.EntityNotFoundException;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.NotAllowedException;
import javax.ws.rs.NotFoundException;

import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
public class GlobalExceptionHandler {

    private final ActiveMQSender jms;


    public GlobalExceptionHandler(ActiveMQSender jms) {
        this.jms = jms;
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return createErrorResponse(BAD_REQUEST, "Request body is missing.");
    }

    @ExceptionHandler({ValidationException.class, WrongOwnerException.class})
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

    @ExceptionHandler(ModelMapperException.class)
    public ResponseEntity<?> handleModelMapperException(Exception e) {
        return createErrorResponse(INTERNAL_SERVER_ERROR, e.getMessage());
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException e, HttpServletResponse response){
        //TODO
        return null;
    }

    private ResponseEntity<?> createErrorResponse(HttpStatus httpStatus, String detailedMessage) {
        return ResponseEntity.status(httpStatus)
                .body(new Error(httpStatus.value(), httpStatus.getReasonPhrase(), detailedMessage));
    }


}
