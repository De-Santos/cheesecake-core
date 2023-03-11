package com.order.service.orderservice.controller.internal;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.order.service.orderservice.dto.WishListResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface InternalApi {
    @Operation(summary = "Check product.", description = "Check product and filed active. Return true if product is exist and active else return false.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> initialization(Long userId);

    @Operation(summary = "Get all wishLists", description = "Develop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<WishListResponse>> getWishList();

    @Operation(summary = "Get all baskets", description = "Develop")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<WishListResponse>> getBasket();
}
