package com.data.tallermodelodatos.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice //para las excepciones que se tengan creadas las captura y las maneja
public class GlobalException {
    @ExceptionHandler(value = ResourceNotFoundException.class) //manejador del evento de la excepcion resourcenotfound
    public ResponseEntity<ErrorMessage> resourceNotFound(ResourceNotFoundException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(HttpStatus.NOT_FOUND.value(), ex.getMessage(), LocalDateTime.now());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
    }
}
