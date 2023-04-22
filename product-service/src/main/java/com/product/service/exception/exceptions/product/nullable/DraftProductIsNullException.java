package com.product.service.exception.exceptions.product.nullable;

import com.product.service.exception.exceptions.product.main.DraftRequestException;

public class DraftProductIsNullException extends DraftRequestException {
    public DraftProductIsNullException() {
    }

    public DraftProductIsNullException(String message) {
        super(message);
    }

    public DraftProductIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public DraftProductIsNullException(Throwable cause) {
        super(cause);
    }

    public DraftProductIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
