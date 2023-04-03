package com.file.receiver.exceptions.exceptioins.use;

import com.file.receiver.exceptions.exceptioins.file.FileException;

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
