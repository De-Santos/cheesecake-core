package com.user.service.exceptions.exceptions.wish.exist;

import com.user.service.exceptions.exceptions.wish.main.WishListException;

import java.util.UUID;

@SuppressWarnings("unused")
public class WishProductAlreadyExistException extends WishListException {

    public static WishProductAlreadyExistException create(Long wishListId, UUID productVersionId) {
        return new WishProductAlreadyExistException(
                String.format("Wish product already exist in wish list by id: %d and version id: %s.", wishListId, productVersionId)
        );
    }

    public WishProductAlreadyExistException() {
    }

    public WishProductAlreadyExistException(String message) {
        super(message);
    }

    public WishProductAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public WishProductAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public WishProductAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
