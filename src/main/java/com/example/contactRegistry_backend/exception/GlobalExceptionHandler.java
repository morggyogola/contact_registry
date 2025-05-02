package com.example.contactRegistry_backend.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ContactCreationException.class)
    ResponseEntity<String> handleContactCreation(ContactCreationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
