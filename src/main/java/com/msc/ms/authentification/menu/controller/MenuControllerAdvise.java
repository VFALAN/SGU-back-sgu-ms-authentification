package com.msc.ms.authentification.menu.controller;

import com.msc.ms.authentification.common.error.DataBaseObjectNotFound;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class MenuControllerAdvise {
    @ExceptionHandler(DataBaseObjectNotFound.class)
    public ResponseEntity<String> DataBaseObjectNotFoundHandler(DataBaseObjectNotFound ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
}
