package com.user.service.exceptions;

@SuppressWarnings("unused")
public class UserNotificationException extends RuntimeException {
    public UserNotificationException() {
    }

    public UserNotificationException(String message) {
        super(message);
    }

    public UserNotificationException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotificationException(Throwable cause) {
        super(cause);
    }

    public UserNotificationException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
