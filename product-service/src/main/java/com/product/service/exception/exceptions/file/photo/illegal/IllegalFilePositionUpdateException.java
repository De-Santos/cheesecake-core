package com.product.service.exception.exceptions.file.photo.illegal;

import com.product.service.exception.exceptions.file.photo.main.FileUpdateException;

public class IllegalFilePositionUpdateException extends FileUpdateException {
    public static IllegalFilePositionUpdateException create(Integer pos) {
        return new IllegalFilePositionUpdateException("Illegal position obtained: " + pos);
    }

    public IllegalFilePositionUpdateException() {
    }

    public IllegalFilePositionUpdateException(String message) {
        super(message);
    }

    public IllegalFilePositionUpdateException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalFilePositionUpdateException(Throwable cause) {
        super(cause);
    }

    public IllegalFilePositionUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
