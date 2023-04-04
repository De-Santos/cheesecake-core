package com.product.service.exception.exceptions.photo.use;


import com.product.service.exception.exceptions.photo.file.FileException;

public class FileInUseException extends FileException {
    public FileInUseException() {
    }

    public FileInUseException(String message) {
        super(message);
    }

    public FileInUseException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileInUseException(Throwable cause) {
        super(cause);
    }

    public FileInUseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
