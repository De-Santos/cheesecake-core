package com.user.service.exceptions.exceptions.user.main;

@SuppressWarnings("unused")
public class UserPrivateDataException extends RuntimeException {

    public UserPrivateDataException() {
    }

    public UserPrivateDataException(String message) {
        super(message);
    }

    public UserPrivateDataException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPrivateDataException(Throwable cause) {
        super(cause);
    }

    public UserPrivateDataException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
