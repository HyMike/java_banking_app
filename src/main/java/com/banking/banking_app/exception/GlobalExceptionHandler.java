package com.banking.banking_app.exception;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.banking.banking_app.exception.InsufficientFundsException;

import javax.naming.InsufficientResourcesException;

@RestControllerAdvice
public class GlobalExceptionHandler extends RuntimeException {
    @ExceptionHandler(InsufficientFundsException.class)
    public ResponseEntity<String> handleFunds(InsufficientFundsException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
