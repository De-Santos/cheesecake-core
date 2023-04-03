package com.product.service.controller.internal;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface InternalApi {

    @Operation(summary = "Check product.", description = "Check product and filed active. Return true if product is exist and active else return false.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> isRealProduct(@PathVariable @NotNull String versionId);

    @Operation(summary = "Check product list.", description = "Check product and filed active.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    void isRealProductSequence(@RequestParam @NotNull List<String> versionIdList);

    @Operation(summary = "Search in products and check the photoId",
            description = "If photo in use return true else return false")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    boolean isPhotoInUse(@RequestParam @NotNull String photoId);

    @Operation(summary = "Search in products and check the List of photoIds",
            description = "If any photo of List was return true else return false")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    boolean isPhotoListInUse(@RequestParam @NotNull List<String> photoIds);

}
