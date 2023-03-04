package com.product.service.productservice.controller;

import com.product.service.productservice.dto.product.ProductRequest;
import ua.cheesecake.dto.ProductResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface ProductApi {
    @Operation(summary = "Create new product", description = "Returns id of the created product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> addPost(@RequestBody @NotNull ProductRequest productRequest);

    @Operation(summary = "Find product by product id", description = "Returns a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    ResponseEntity<ProductResponse> getPost(@PathVariable String id);

    @Operation(summary = "Find all products", description = "Returns list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    ResponseEntity<List<ProductResponse>> getAll();

    @Operation(summary = "Update a product by id", description = "Update a product fields (text, image, ect.)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id or body supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    ResponseEntity<ProductResponse> updateProduct(@PathVariable String id,
                                                         @RequestBody ProductRequest productRequest);

    @Operation(summary = "Set off/on product by id", description = "Change field active/inactive, return product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    ResponseEntity<ProductResponse> editProduct(@PathVariable String id);

    @Operation(summary = "Delete all product DEVELOP", description = "ONLY FOR DEVELOPERS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    void deleteAll();

    @Operation(summary = "Get all archived products", description = "ONLY FOR DEVELOPERS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    List<ProductResponse> getArchive();
}
