package com.murillo.maciel.store.resources.exceptions;

import com.murillo.maciel.store.services.exceptions.ObjNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ResourceExceptionHandler
{
    @ExceptionHandler(ObjNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjNotFoundException e, HttpServletRequest request)
    {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}