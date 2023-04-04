package com.product.service.exception.exceptions.photo.exist;

import com.product.service.exception.exceptions.photo.file.FileException;

public class FileAlreadyExistException extends FileException {
    public FileAlreadyExistException() {
    }

    public FileAlreadyExistException(String message) {
        super(message);
    }

    public FileAlreadyExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileAlreadyExistException(Throwable cause) {
        super(cause);
    }

    public FileAlreadyExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
