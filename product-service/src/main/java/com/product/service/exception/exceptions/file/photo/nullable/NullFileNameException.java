package com.product.service.exception.exceptions.file.photo.nullable;


import com.product.service.exception.exceptions.file.photo.main.NullFileException;

@SuppressWarnings("unused")
public class NullFileNameException extends NullFileException {
    public NullFileNameException() {
    }

    public NullFileNameException(String message) {
        super(message);
    }

    public NullFileNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullFileNameException(Throwable cause) {
        super(cause);
    }

    public NullFileNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
