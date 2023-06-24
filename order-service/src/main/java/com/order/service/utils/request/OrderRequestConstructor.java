package com.order.service.utils.request;

import com.order.service.dao.OrderMetadataRepository;
import com.order.service.dao.OrderProductRepository;
import com.order.service.dao.OrderRepository;
import com.order.service.dao.PaymentDataRepository;
import com.order.service.dto.OrderRequest;
import com.order.service.dto.UpdateProcessStatusRequest;
import com.order.service.entities.Order;
import com.order.service.entities.additional.ProcessStatus;
import com.order.service.exception.exceptions.order.found.OrderNotFoundException;
import com.order.service.utils.builder.EntityBuilder;
import com.order.service.utils.calculator.PaymentCalculator;
import com.order.service.utils.request.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class OrderRequestConstructor {
    private final OrderRepository orderRepository;
    private final OrderProductRepository orderProductRepository;
    private final OrderMetadataRepository orderMetadataRepository;
    private final PaymentDataRepository paymentDataRepository;
    private final EntityBuilder builder;
    private final JdbcAccelerator accelerator;
    private final PaymentCalculator paymentCalculator;

    @Transactional
    public Order newOrder(OrderRequest orderRequest) {
        Order order = orderRepository.save(builder.buildOrder());
        order.setOrderMetadata(orderMetadataRepository.save(builder.buildOrderMetadataFrom(order, orderRequest)));
        order.setPaymentData(paymentDataRepository.save(paymentCalculator.calculatePaymentData(order, orderRequest)));
        order.setOrderProducts(orderProductRepository.saveAll(paymentCalculator.calculateOrderProducts(order, orderRequest)));
        return order;
    }

    public Order disableOrder(Long orderId) {
        accelerator.disableOrder(orderId);
        return orderRepository.findById(orderId)
                .orElseThrow(() -> OrderNotFoundException.create(orderId));
    }

    public Order updateOrderStatus(Long id, UpdateProcessStatusRequest processStatus) {
        accelerator.updateStatus(id, ProcessStatus.valueOf(processStatus.name()));
        return orderRepository.findById(id)
                .orElseThrow(() -> OrderNotFoundException.create(id));
    }
}
