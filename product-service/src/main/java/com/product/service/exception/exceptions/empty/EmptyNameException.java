package com.product.service.exception.exceptions.empty;

import com.product.service.exception.exceptions.main.ProductRequestException;

public class EmptyNameException extends ProductRequestException {
    public EmptyNameException() {
    }

    public EmptyNameException(String message) {
        super(message);
    }

    public EmptyNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyNameException(Throwable cause) {
        super(cause);
    }

    public EmptyNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
