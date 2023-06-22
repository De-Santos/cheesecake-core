package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.RejectOrderRequestException;

@SuppressWarnings("unused")
public class AdminIdIsNullRequestException extends RejectOrderRequestException {

    public static AdminIdIsNullRequestException create() {
        return new AdminIdIsNullRequestException("Admin id must not be null.");
    }

    public AdminIdIsNullRequestException() {
    }

    public AdminIdIsNullRequestException(String message) {
        super(message);
    }

    public AdminIdIsNullRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdminIdIsNullRequestException(Throwable cause) {
        super(cause);
    }

    public AdminIdIsNullRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
