package com.user.service.exceptions.exceptions.user.found;

import com.user.service.exceptions.exceptions.user.main.UserPrivateDataException;

@SuppressWarnings("unused")
public class UserPrivateDataNotFoundException extends UserPrivateDataException {

    public static UserPrivateDataNotFoundException create(Long userId) {
        return new UserPrivateDataNotFoundException("User private data doesn't found by user id: " + userId);
    }

    public UserPrivateDataNotFoundException() {
    }

    public UserPrivateDataNotFoundException(String message) {
        super(message);
    }

    public UserPrivateDataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserPrivateDataNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserPrivateDataNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
