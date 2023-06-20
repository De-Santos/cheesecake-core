package com.order.service.controller;

import com.order.service.dto.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;


@SuppressWarnings("unused")
public interface OrderApi {

    @Operation(summary = "Create new order for single product --User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest);

    @Operation(summary = "Disable order by id --User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<OrderResponse> disableOrder(@PathVariable Long id);

    @Operation(summary = "Reject order by id --Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<OrderResponse> rejectOrder(@PathVariable Long id, @RequestBody RejectOrderRequest rejectOrderRequest);

    @Operation(summary = "Get order info by id --User")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<OrderInfoResponse> getOrderInfo(@PathVariable("id") Long id);

    @Operation(summary = "Update order by id --Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<OrderResponse> updateOrder(@RequestBody UpdateOrderRequest updateOrderRequest);

    @Operation(summary = "Update order status by id --Admin")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<OrderResponse> updateOrderStatus(@PathVariable Long id, @RequestParam UpdateProcessStatusRequest processStatus);
}
