package com.puc.cmfback.exception;

import com.puc.cmfback.exception.errors.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NegocioException.class)
    public ResponseEntity<?> handleUsuarioException(NegocioException nx) {
        return ResponseEntity.status(nx.getStatus()).body(nx.getMessage());
    }
}
