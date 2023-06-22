package com.order.service.exception.exceptions.product.exceeded;

import com.order.service.dto.OrderProductRequest;
import com.order.service.exception.exceptions.product.main.OrderProductException;

@SuppressWarnings("unused")
public class OrderProductCountExceededException extends OrderProductException {

    public static OrderProductCountExceededException create(OrderProductRequest orderProductRequest, Integer maxProductCount) {
        return new OrderProductCountExceededException(
                String.format(
                        "Product count must be less than %s. product by version id: %s, has count: %s",
                        maxProductCount,
                        orderProductRequest.getProductVersionId(),
                        orderProductRequest.getCount()
                )
        );
    }

    public OrderProductCountExceededException() {
    }

    public OrderProductCountExceededException(String message) {
        super(message);
    }

    public OrderProductCountExceededException(String message, Throwable cause) {
        super(message, cause);
    }

    public OrderProductCountExceededException(Throwable cause) {
        super(cause);
    }

    public OrderProductCountExceededException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
