package com.order.service.utils.builder;

import com.order.service.dto.OrderRequest;
import com.order.service.entities.Order;
import com.order.service.entities.OrderMetadata;
import com.order.service.entities.additional.ProcessStatus;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class EntityBuilder {

    public Order buildOrder() {
        return Order.builder()
                .processStatus(ProcessStatus.PENDING)
                .build();
    }

    public OrderMetadata buildOrderMetadataFrom(Order order, OrderRequest orderRequest) {
        return OrderMetadata.builder()
                .order(order)
                .userId(orderRequest.getUserId())
                .message(orderRequest.getMessage())
                .requiredDoneTime(orderRequest.getRequiredDoneTime())
                .creationTime(new Date())
                .build();
    }
}
