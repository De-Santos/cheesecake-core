package com.file.receiver.exceptions.exceptioins.file;

public class TagNameException extends RuntimeException {
    public TagNameException() {
    }

    public TagNameException(String message) {
        super(message);
    }

    public TagNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagNameException(Throwable cause) {
        super(cause);
    }

    public TagNameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
