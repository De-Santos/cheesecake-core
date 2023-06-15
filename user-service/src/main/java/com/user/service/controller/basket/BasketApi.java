package com.user.service.controller.basket;


import com.user.service.dto.basket.BasketProductResponse;
import com.user.service.dto.basket.BasketRequest;
import com.user.service.dto.basket.BasketResponse;
import com.user.service.dto.basket.DeleteBasketProductRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@SuppressWarnings("unused")
public interface BasketApi {
    @Operation(summary = "Add item in basket", description = "Add product in basket only if the product does not exist in basket, return 'true'. If product exist in basket, return 'false'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BasketProductResponse> addItem(@RequestBody BasketRequest basketRequest);

    @Operation(summary = "Delete item from basket", description = "Delete product in basket only if the product exist in basket, return 'true'. If product does not exist, return 'false'.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> deleteItem(@RequestBody DeleteBasketProductRequest deleteBasketProductRequest);

    @Operation(summary = "Update basket", description = "If product exist in basket, return 'true' else return 'false'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BasketProductResponse> updateBasketProduct(@RequestBody BasketRequest basketRequest);

    @Operation(summary = "Check product in basket", description = "If product exist in basket, return 'true' else return 'false'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> checkItem(@RequestBody BasketRequest basketRequest);

    @Operation(summary = "Get basket by userId", description = "Return basket")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<BasketResponse> getBasket(@PathVariable("id") Long userId);
}
