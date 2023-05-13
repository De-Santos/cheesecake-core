package com.product.service.controller.product;

import com.product.service.dto.photo.DraftProductDto;
import com.product.service.dto.product.ProductResponse;
import com.product.service.dto.product.SailProductRequest;
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
import java.util.UUID;

public interface ProductApi {
    @Operation(summary = "Create new product from draft product", description = "Returns id of the created product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UUID> addProduct(@PathVariable("draftId") @NotNull Long draftId);

    @Operation(summary = "Get all archived products", description = "ONLY FOR DEVELOPERS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ProductResponse>> getArchive();

    @Operation(summary = "Update a product by draft product id", description = "Update a product fields (text, image, ect.)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id or body supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UUID> updateProduct(@PathVariable("draftId") Long id);

    @Operation(summary = "Set off/on product by id", description = "Change field active/inactive, return product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> editProduct(@PathVariable UUID id);

    @Operation(summary = "Delete all product DEVELOP", description = "ONLY FOR DEVELOPERS")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    void deleteAll();

    @Operation(summary = "Find product by product versionId id", description = "Returns a product by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> getProduct(@PathVariable("versionId") UUID versionId);

    @Operation(summary = "Find all products", description = "Returns list of products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
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
    ResponseEntity<ProductResponse> sailMode(@RequestBody SailProductRequest sailProductRequest);

    @Operation(summary = "Crate new draft from product by version id",
            description = "Crate new draft product with data from product by versionId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> toDraftProduct(@PathVariable("id") UUID versionId);

    @Operation(summary = "Returns id of new empty draft", description = "Returns id of the created draft product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> addDraft();

    @Operation(summary = "Returns draft by id", description = "Returns draft by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductDto> getDraft(@PathVariable("draftId") Long id);

    @Operation(summary = "Returns all drafts id", description = "Returns list of drafts id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<Long>> getDrafts();


    @Operation(summary = "Update draft", description = "Update draft by id from dto and returns updated draft")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductDto> updateDraftProduct(@RequestBody DraftProductDto draftProductDto);

    @Operation(summary = "Delete draft by id", description = "Returns deleted draft product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductDto> deleteDraftProduct(@PathVariable("draftId") Long id);
}
