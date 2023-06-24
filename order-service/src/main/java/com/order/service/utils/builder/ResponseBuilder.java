package com.order.service.utils.builder;

import com.order.service.dto.OrderResponse;
import com.order.service.entities.Order;
import com.order.service.entities.OrderProduct;
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
}
