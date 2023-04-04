package com.product.service.exception.exceptions.photo.exceeded;

import com.product.service.exception.exceptions.photo.file.FileException;

public class FileSizeExceededException extends FileException {
    public FileSizeExceededException() {
    }

    public FileSizeExceededException(String message) {
        super(message);
    }

    public FileSizeExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileSizeExceededException(Throwable cause) {
        super(cause);
    }

    public FileSizeExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
