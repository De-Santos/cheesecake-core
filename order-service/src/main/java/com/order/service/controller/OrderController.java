package com.order.service.controller;

import com.order.service.dto.*;
import com.order.service.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/order")
public class OrderController implements OrderApi {
    private final OrderService orderService;

    @Override
    @PostMapping()
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        log.info("Create order by request: {}", orderRequest);
        return null;
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<OrderResponse> disableOrder(@PathVariable("id") Long orderId) {
        log.info("Disable order by id: {}", orderId);
        return null;
    }

    @Override
    @PatchMapping("/{id}/reject")
    public ResponseEntity<OrderResponse> rejectOrder(@PathVariable Long id, @RequestBody RejectOrderRequest rejectOrderRequest) {
        log.info("Reject order by id: {} with request: {}", id, rejectOrderRequest);
        return null;
    }

    @Override
    @GetMapping("/{id}/info")
    public ResponseEntity<OrderInfoResponse> getOrderInfo(@PathVariable("id") Long id) {
        log.info("Get order info by id: {}", id);
        return null;
    }

    @Override
    @PatchMapping()
    public ResponseEntity<OrderResponse> updateOrder(UpdateOrderRequest updateOrderRequest) {
        log.info("Update order by request: {}", updateOrderRequest);
        return null;
    }

    @Override
    @PatchMapping("/{id}/status")
    public ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long id, @RequestParam UpdateProcessStatusRequest processStatus) {
        log.info("Update order status by id: {} with status: {}", id, processStatus);
        return null;
    }
}
