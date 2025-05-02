package com.example.contactRegistry_backend.exception;

public class ContactCreationException extends RuntimeException {
    public ContactCreationException(String message) {
        super(message);
    }

    public ContactCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public ContactCreationException(Throwable cause) {
        super(cause);
    }
}
