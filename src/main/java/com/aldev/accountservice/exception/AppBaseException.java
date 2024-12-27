package com.aldev.accountservice.exception;

public class AppBaseException extends RuntimeException {
    public AppBaseException(String message) {
        super(message);
    }

    public AppBaseException(String message, Throwable cause) {
        super(message, cause);
    }
}