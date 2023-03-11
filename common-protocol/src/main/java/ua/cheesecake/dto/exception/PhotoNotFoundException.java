package ua.cheesecake.dto.exception;


public class PhotoNotFoundException extends RuntimeException {

    public PhotoNotFoundException(String message) {
        super(message);
    }

    public PhotoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PhotoNotFoundException(Throwable cause) {
        super(cause);
    }

    public PhotoNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public PhotoNotFoundException() {
    }
}