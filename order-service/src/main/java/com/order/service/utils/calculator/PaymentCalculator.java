package com.order.service.utils.calculator;

import com.order.service.dto.OrderRequest;
import com.order.service.entities.Order;
import com.order.service.entities.OrderProduct;
import com.order.service.entities.PaymentData;
import com.order.service.utils.request.accelerator.JdbcAccelerator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
@RequiredArgsConstructor
public class PaymentCalculator {
    private final JdbcAccelerator accelerator;

    public PaymentData calculatePaymentData(Order order, OrderRequest orderRequest) {
        BigDecimal sum = this.calculateSummaryPrice(orderRequest);
        return PaymentData.builder()
                .order(order)
                .realTotalPrice(sum)
                .totalPrice(sum)
                .build();

    }

    private BigDecimal calculateSummaryPrice(OrderRequest orderRequest) {
        return orderRequest.getProducts().stream()
                .map(product -> accelerator.getProductPriceByVersionId(product.getProductVersionId())
                        .multiply(BigDecimal.valueOf(product.getCount())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public List<OrderProduct> calculateOrderProducts(Order order, OrderRequest orderRequest) {
        return orderRequest.getProducts().stream()
                .map(product -> OrderProduct.builder()
                        .order(order)
                        .productVersionId(product.getProductVersionId())
                        .count(product.getCount())
                        .price(accelerator.getProductPriceByVersionId(product.getProductVersionId()))
                        .build())
                .toList();
    }
}
