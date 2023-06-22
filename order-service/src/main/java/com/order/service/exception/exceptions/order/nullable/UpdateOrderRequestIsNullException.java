package com.order.service.exception.exceptions.order.nullable;

import com.order.service.exception.exceptions.order.main.UpdateOrderRequestException;

@SuppressWarnings("unused")
public class UpdateOrderRequestIsNullException extends UpdateOrderRequestException {

    public static UpdateOrderRequestIsNullException create() {
        return new UpdateOrderRequestIsNullException("Update order request must not be null.");
    }

    public UpdateOrderRequestIsNullException() {
    }

    public UpdateOrderRequestIsNullException(String message) {
        super(message);
    }

    public UpdateOrderRequestIsNullException(String message, Throwable cause) {
        super(message, cause);
    }

    public UpdateOrderRequestIsNullException(Throwable cause) {
        super(cause);
    }

    public UpdateOrderRequestIsNullException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
