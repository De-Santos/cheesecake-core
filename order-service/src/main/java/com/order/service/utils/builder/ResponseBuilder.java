package com.order.service.utils.builder;

import com.order.service.dto.OrderInfoResponse;
import com.order.service.dto.OrderResponse;
import com.order.service.dto.RejectOrderResponse;
import com.order.service.entities.Order;
import com.order.service.entities.OrderProduct;
import com.order.service.entities.RejectOrderCause;
import org.springframework.stereotype.Component;

@Component("MyResponseBuilder")
public class ResponseBuilder {
    public OrderResponse build(Order order) {
        return OrderResponse.builder()
                .id(order.getId())
                .userId(order.getOrderMetadata().getUserId())
                .creationTime(order.getOrderMetadata().getCreationTime())
                .totalPrice(order.getPaymentData().getTotalPrice())
                .realTotalPrice(order.getPaymentData().getRealTotalPrice())
                .processStatus(order.getProcessStatus())
                .requiredDoneTime(order.getOrderMetadata().getRequiredDoneTime())
                .orderProductIdList(
                        order
                                .getOrderProducts()
                                .stream()
                                .map(OrderProduct::getId
                                ).toList()
                )
                .build();
    }

    public OrderInfoResponse buildInfo(Order order) {
        return OrderInfoResponse.builder()
                .id(order.getId())
                .userId(order.getOrderMetadata().getUserId())
                .creationTime(order.getOrderMetadata().getCreationTime())
                .totalPrice(order.getPaymentData().getTotalPrice())
                .realTotalPrice(order.getPaymentData().getRealTotalPrice())
                .processStatus(order.getProcessStatus())
                .requiredDoneTime(order.getOrderMetadata().getRequiredDoneTime())
                .build();
    }

    public RejectOrderResponse build(Long orderId, RejectOrderCause rejectOrderCause) {
        return RejectOrderResponse.builder()
                .userId(rejectOrderCause.getUserId())
                .orderId(orderId)
                .message(rejectOrderCause.getMessage())
                .build();
    }
}
