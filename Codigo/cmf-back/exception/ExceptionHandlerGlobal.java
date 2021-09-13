package com.puc.cmfback.exception;

import com.puc.cmfback.exception.errors.UsuarioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerGlobal extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<Object> handleUsuarioException(UsuarioException ux) {
        return ResponseEntity.status(ux.getStatus()).body(ux.getMessage());
    }
}
