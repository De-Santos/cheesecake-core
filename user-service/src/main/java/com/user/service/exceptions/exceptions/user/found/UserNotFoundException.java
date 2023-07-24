package com.user.service.exceptions.exceptions.user.found;

import com.user.service.exceptions.exceptions.user.main.UserException;

@SuppressWarnings("unused")
public class UserNotFoundException extends UserException {

    public static UserNotFoundException create(Long id) {
        return new UserNotFoundException("User not found by id: " + id);
    }

    public UserNotFoundException() {
    }

    public UserNotFoundException(String message) {
        super(message);
    }

    public UserNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNotFoundException(Throwable cause) {
        super(cause);
    }

    public UserNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
