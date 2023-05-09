package com.product.service.exception.exceptions.product.found;

import com.product.service.exception.exceptions.product.main.DraftRequestException;

public class DraftProductNotFoundException extends DraftRequestException {

    public static DraftProductNotFoundException create(Long id) {
        return new DraftProductNotFoundException("Draft product not found: " + id);
    }

    public DraftProductNotFoundException() {
    }

    public DraftProductNotFoundException(String message) {
        super(message);
    }

    public DraftProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public DraftProductNotFoundException(Throwable cause) {
        super(cause);
    }

    public DraftProductNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
