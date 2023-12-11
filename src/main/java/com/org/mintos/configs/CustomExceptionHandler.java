package com.org.mintos.configs;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<?> handleMyCustomException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
