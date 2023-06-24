package com.order.service.service;

import com.order.service.dto.*;
import com.order.service.utils.builder.ResponseBuilder;
import com.order.service.utils.request.OrderRequestConstructor;
import com.order.service.utils.validate.RequestValidator;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Log4j2
@Service
@RequiredArgsConstructor
public class OrderService {
    private final RequestValidator requestValidator;
    private final OrderRequestConstructor orderRequestConstructor;
    private final ResponseBuilder responseBuilder;

    public OrderResponse newOrder(OrderRequest orderRequest) {
        requestValidator.validateOrderRequest(orderRequest);
        return responseBuilder.build(orderRequestConstructor.newOrder(orderRequest));
    }

    public OrderResponse newOrderFromBasket(Long basketId) {
        requestValidator.validateBasketById(basketId);
        return null;
    }

    public OrderResponse disableOrder(Long orderId) {
        return responseBuilder.build(orderRequestConstructor.disableOrder(orderId));
    }

    public OrderResponse rejectOrder(Long id, RejectOrderRequest rejectOrderRequest) {
        requestValidator.validateOrderRejectRequest(id, rejectOrderRequest);
        return null;
    }

    @SuppressWarnings("unused")
    public OrderInfoResponse getOrderInfo(Long id) {
        return null;
    }

    public OrderResponse updateOrder(UpdateOrderRequest updateOrderRequest) {
        requestValidator.validateUpdateOrderRequest(updateOrderRequest);
        return null;
    }

    public OrderResponse updateOrderStatus(Long id, UpdateProcessStatusRequest processStatus) {
        requestValidator.validateUpdateOrderStatusRequest(processStatus);
        return responseBuilder.build(orderRequestConstructor.updateOrderStatus(id, processStatus));
    }
}
