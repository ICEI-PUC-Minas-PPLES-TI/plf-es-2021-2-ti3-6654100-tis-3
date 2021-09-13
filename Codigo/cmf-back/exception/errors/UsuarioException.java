package com.puc.cmfback.exception.errors;

import org.springframework.http.HttpStatus;

public class UsuarioException extends RuntimeException {

    private static final String DEFAULT_MESSAGE = "Entrar em contato com o administrador do sistema: ";
    private static final HttpStatus DEFAULT_STATUS = HttpStatus.INTERNAL_SERVER_ERROR;

    private final HttpStatus status;

    public UsuarioException() {
        super(DEFAULT_MESSAGE);
        this.status = DEFAULT_STATUS;
    }

    public UsuarioException(String mensagem) {
        super(mensagem);
        this.status = DEFAULT_STATUS;
    }

    public UsuarioException(String mensagem, HttpStatus status) {
        super(mensagem);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
