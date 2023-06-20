package com.product.service.exception.exceptions.file.photo.invalid;


import com.product.service.exception.exceptions.file.photo.main.FileException;

@SuppressWarnings("unused")
public class InvalidFileTypeException extends FileException {
    public InvalidFileTypeException() {
    }

    public InvalidFileTypeException(String message) {
        super(message);
    }

    public InvalidFileTypeException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidFileTypeException(Throwable cause) {
        super(cause);
    }

    public InvalidFileTypeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
