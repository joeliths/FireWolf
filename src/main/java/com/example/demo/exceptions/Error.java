package com.example.demo.exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

public class Error implements Serializable {

    private final LocalDateTime timeOccurred;
    private final int statusCode;
    private final String statusMessage;
    private final String detailedMessage;
    private final String path;

    public Error(int statusCode, String statusMessage, String detailedMessage) {
        this.timeOccurred = LocalDateTime.now();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.detailedMessage = detailedMessage;
        this.path = getRequestPath();
    }

    private String getRequestPath() {
        String requestUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        String baseUri = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
        return requestUri.substring(baseUri.length());
    }

    public LocalDateTime getTimeOccurred() {
        return timeOccurred;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public String getDetailedMessage() {
        return detailedMessage;
    }

    public String getPath() {
        return path;
    }
}