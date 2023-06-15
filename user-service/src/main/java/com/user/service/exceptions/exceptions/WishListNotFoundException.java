package com.user.service.exceptions.exceptions;

@SuppressWarnings("unused")
public class WishListNotFoundException extends RuntimeException {

    public static WishListNotFoundException create(Long id) {
        return new WishListNotFoundException("Wish list not found by id: " + id);
    }

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
