package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.RejectOrderRequestException;

@SuppressWarnings("unused")
public class RejectOrderExceptionIsNullRequestException extends RejectOrderRequestException {

    public static RejectOrderExceptionIsNullRequestException create() {
        return new RejectOrderExceptionIsNullRequestException("Reject order request must not be null.");
    }

    public RejectOrderExceptionIsNullRequestException() {
    }

    public RejectOrderExceptionIsNullRequestException(String message) {
        super(message);
    }

    public RejectOrderExceptionIsNullRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public RejectOrderExceptionIsNullRequestException(Throwable cause) {
        super(cause);
    }

    public RejectOrderExceptionIsNullRequestException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
