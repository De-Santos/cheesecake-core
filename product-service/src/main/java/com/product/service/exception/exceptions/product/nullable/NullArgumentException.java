package com.product.service.exception.exceptions.product.nullable;

import com.product.service.exception.exceptions.product.main.ModifyingRequestException;

@SuppressWarnings("unused")
public class NullArgumentException extends ModifyingRequestException {
    public NullArgumentException() {
    }

    public NullArgumentException(String message) {
        super(message);
    }

    public NullArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullArgumentException(Throwable cause) {
        super(cause);
    }

    public NullArgumentException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
