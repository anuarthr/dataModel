package com.data.tallermodelodatos.exception;

public class ClientNotFoundException extends ResourceNotFoundException{
    public ClientNotFoundException() {
        super();
    }

    public ClientNotFoundException(String message) {
        super(message);
    }

    public ClientNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
