package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.OrderRequestException;

@SuppressWarnings("unused")
public class UserNameIsNullException extends OrderRequestException {

    public static UserNameIsNullException create() {
        return new UserNameIsNullException("User name must be null");
    }

    public UserNameIsNullException() {
    }

    public UserNameIsNullException(String message) {
        super(message);
    }

    public UserNameIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserNameIsNullException(Throwable cause) {
        super(cause);
    }

    public UserNameIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
