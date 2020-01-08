package com.example.demo.exceptions;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Error implements Serializable {

    private final String timeOccurred;
    private final int statusCode;
    private final String statusMessage;
    private final String detailedMessage;
    private final String path;

    public Error(int statusCode, String statusMessage, String detailedMessage) {
        this.timeOccurred = LocalDateTime.now().toString();
        this.statusCode = statusCode;
        this.statusMessage = statusMessage;
        this.detailedMessage = detailedMessage;
        this.path = getRequestPath();
    }

    private String getRequestPath() {
        try {
            String requestUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
            String baseUri = ServletUriComponentsBuilder.fromCurrentContextPath().toUriString();
            return requestUri.substring(baseUri.length());
        } catch (IllegalStateException e) {
            return "Must be logged in to obtain request path.";
        }
    }

    public String getTimeOccurred() {
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

    @Override
    public String toString() {
        return "Error{" +
                "timeOccurred='" + timeOccurred + '\'' +
                ", statusCode=" + statusCode +
                ", statusMessage='" + statusMessage + '\'' +
                ", detailedMessage='" + detailedMessage + '\'' +
                ", path='" + path + '\'' +
                '}';
    }
}