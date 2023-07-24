package com.example.javaspringboot.controllers;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> blankNameException(MethodArgumentNotValidException e) {
        return new ResponseEntity<>(e.getFieldError().getDefaultMessage(), HttpStatusCode.valueOf(500));
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> blankNameException(ConstraintViolationException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatusCode.valueOf(500));
    }
}
