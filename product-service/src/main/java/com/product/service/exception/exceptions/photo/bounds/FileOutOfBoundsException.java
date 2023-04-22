package com.product.service.exception.exceptions.photo.bounds;

import com.product.service.exception.exceptions.photo.file.FileException;

public class FileOutOfBoundsException extends FileException {
    public FileOutOfBoundsException() {
    }

    public FileOutOfBoundsException(String message) {
        super(message);
    }

    public FileOutOfBoundsException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileOutOfBoundsException(Throwable cause) {
        super(cause);
    }

    public FileOutOfBoundsException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
