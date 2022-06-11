package com.ggcorreia.randomjoke.exception;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * Response wrapper for API Errors
 */
public class ApiErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();
    private String message;
    private HttpStatus status;

    public ApiErrorResponse(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }
}
