package com.product.service.controller.product;

import com.product.service.dto.draft.DraftProductDto;
import com.product.service.dto.product.ModifyingProductRequest;
import com.product.service.dto.product.ProductResponse;
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
    ResponseEntity<ProductResponse> addProduct(@PathVariable("draftId") @NotNull String draftId);

    @Operation(summary = "Get all archived products", description = "ONLY FOR DEVELOPERS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ProductResponse>> getArchive();

    @Operation(summary = "Update a product by id", description = "Update a product fields (text, image, ect.)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id or body supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> updateProduct(@PathVariable("draftId") String id);

    @Operation(summary = "Set off/on product by id", description = "Change field active/inactive, return product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> editProduct(@PathVariable String id);

    @Operation(summary = "Delete all product DEVELOP", description = "ONLY FOR DEVELOPERS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    void deleteAll();

    @Operation(summary = "Find product by product id", description = "Returns a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> getProduct(@PathVariable String id);

    @Operation(summary = "Find all products", description = "Returns list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ProductResponse>> getAll();

    @Operation(summary = "Create sail for a product",
            description = "If sailPrice bigger than zero" +
                    "\nIf sail price equals 0 sail mode is disable")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> sailMode(@RequestBody ModifyingProductRequest modifyingProductRequest);

    @Operation(summary = "Crate new draft from product",
            description = "Crate new draft product with data from product by versionId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductDto> toDraftProduct(@PathVariable("id") String versionId);

    @Operation(summary = "Create new product", description = "Returns id of the created product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> addDraftProduct();


    @Operation(summary = "Create new product", description = "Returns id of the created product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductDto> updateDraftProduct(@RequestBody DraftProductDto draftProductDto);

    @Operation(summary = "Create new product", description = "Returns id of the created product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductDto> deleteDraftProduct(@PathVariable("draftId") String id);
}
