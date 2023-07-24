package com.product.service.exception.exceptions.tag.invalid;

import com.product.service.exception.exceptions.tag.main.TagException;

@SuppressWarnings("unused")
public class InvalidTagNameException extends TagException {

    public static InvalidTagNameException create() {
        return new InvalidTagNameException("Tag name is invalid or blank");
    }

    public InvalidTagNameException() {
    }

    public InvalidTagNameException(String message) {
        super(message);
    }

    public InvalidTagNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidTagNameException(Throwable cause) {
        super(cause);
    }

    public InvalidTagNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
