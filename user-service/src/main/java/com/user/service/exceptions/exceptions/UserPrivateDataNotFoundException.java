package com.user.service.exceptions.exceptions;

public class UserPrivateDataNotFoundException extends RuntimeException{

    public UserPrivateDataNotFoundException() {
    }

    public UserPrivateDataNotFoundException(String message) {
        super(message);
    }

    public UserPrivateDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPrivateDataNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserPrivateDataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
