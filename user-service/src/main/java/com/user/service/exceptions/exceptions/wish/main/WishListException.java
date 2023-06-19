package com.user.service.exceptions.exceptions.wish.main;

@SuppressWarnings("unused")
public class WishListException extends RuntimeException {

    public WishListException() {
    }

    public WishListException(String message) {
        super(message);
    }

    public WishListException(String message, Throwable cause) {
        super(message, cause);
    }

    public WishListException(Throwable cause) {
        super(cause);
    }

    public WishListException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
