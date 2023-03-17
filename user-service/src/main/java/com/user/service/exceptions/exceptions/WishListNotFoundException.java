package com.user.service.exceptions.exceptions;

public class WishListNotFoundException extends RuntimeException {

    public WishListNotFoundException() {
    }

    public WishListNotFoundException(String message) {
        super(message);
    }

    public WishListNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public WishListNotFoundException(Throwable cause) {
        super(cause);
    }

    public WishListNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
