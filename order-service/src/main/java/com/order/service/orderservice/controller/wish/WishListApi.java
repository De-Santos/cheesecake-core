package com.order.service.orderservice.controller.wish;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.order.service.orderservice.dto.WishListRequest;

import jakarta.validation.constraints.NotNull;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface WishListApi {
    @Operation(summary = "Add item in wishList", description = "Return 'true' if operation was successful else return 'false'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> addItem(@RequestBody @NotNull WishListRequest wishListRequest);

    @Operation(summary = "delete item in wishList", description = "Return 'true' if operation was successful else return 'false'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> deleteItem(@RequestBody @NotNull WishListRequest wishListRequest);

    @Operation(summary = "Check item in user wishList", description = "Return 'true' if product in user's wishList else return 'false'")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> checkItem(@RequestBody @NotNull WishListRequest wishListRequest);


    @Operation(summary = "Get wishList by userId", description = "Return List of products in user's wishList")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<String>> getWishList(@PathVariable(name = "id") @NotNull String userId);
}
