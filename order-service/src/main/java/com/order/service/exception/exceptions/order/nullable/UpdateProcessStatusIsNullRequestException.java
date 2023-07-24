package com.order.service.exception.exceptions.order.nullable;


import com.order.service.exception.exceptions.order.main.UpdateOrderRequestException;

@SuppressWarnings("unused")
public class UpdateProcessStatusIsNullRequestException extends UpdateOrderRequestException {

    public static UpdateProcessStatusIsNullRequestException create() {
        return new UpdateProcessStatusIsNullRequestException("Process status must not be null.");
    }

    public UpdateProcessStatusIsNullRequestException() {
    }

    public UpdateProcessStatusIsNullRequestException(String message) {
        super(message);
    }

    public UpdateProcessStatusIsNullRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateProcessStatusIsNullRequestException(Throwable cause) {
        super(cause);
    }

    public UpdateProcessStatusIsNullRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
