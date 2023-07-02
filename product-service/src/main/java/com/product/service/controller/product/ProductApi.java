package com.product.service.controller.product;

import com.product.service.dto.product.DraftProductRequest;
import com.product.service.dto.product.DraftProductResponse;
import com.product.service.dto.product.ProductResponse;
import com.product.service.dto.product.SaleProductRequest;
import com.product.service.dto.tag.TagRequest;
import com.product.service.dto.tag.TagResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public interface ProductApi {
    @Operation(summary = "Create a new product from a draft product",
            description = """
                    Returns the UUID of the new product.

                    Process:
                       - Delete the draft product by ID.
                       - Create a new product from the draft product.
                       - Return the ID of the new product.

                    Rules:
                       - If the draft product has invalid data, an exception will be thrown.
                       - If the draft product is not found by ID, an exception will be thrown.
                       - If the draft product has a parent product, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UUID> addProduct(@PathVariable("draftId") @NotNull Long draftId);

    @Operation(summary = "Get all archived products",
            description = "`ONLY FOR DEVELOP`")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<ProductResponse>> getArchive();

    @Operation(summary = "Update the product using the draft product",
            description = """
                    Returns the UUID of the updated product.

                    Process:
                       - Get the product by the draft's parent ID.
                       - Update the product's data.
                       - Delete the draft product.
                       - Return the updated product's ID.

                    Rules:
                       - If the draft product is not found by ID, an exception will be thrown.
                       - If the draft product does not have a parent ID, an exception will be thrown.
                       - If the draft product's data is invalid, an exception will be thrown.
                       - If the draft product's parent is an archived product, a new product will be created.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid ID or body supplied"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UUID> updateProduct(@PathVariable("draftId") Long id);

    @Operation(summary = "Activate or Deactivate the product",
            description = """
                    Returns the edited product.
                                        
                    Process:
                       - Edit the product by id.
                       - if the product is activated -> deactivate.
                       - if the product is deactivated -> activate.
                                        
                    Rules:
                       - if the product doesn't found by id, an exception will be thrown.
                       - if the product is archived product, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id"),
            @ApiResponse(responseCode = "404", description = "Post doesn't found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> editProduct(@PathVariable UUID id);

    @Operation(summary = "Delete all products DEVELOP", description = "`ONLY FOR DEVELOP`")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    void deleteAll();

    @Operation(summary = "Get a product",
            description = """
                    Returns a product.
                    Get the product by version_id.
                    If the product is archived, the 'active_version_id' field will contain the `UUID` of the active product.
                                        
                    Rules:
                       - If the product is not found, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> getProduct(@PathVariable("versionId") UUID versionId);

    @Operation(summary = "Get all products",
            description = "Returns a list of product's version_id: `UUID`")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UUID>> getAllProducts();

    @Operation(summary = "Sale mode for a product",
            description = """
                    Returns the product with the sale price.
                                        
                    Rules:
                       - If the product is not found by version_id, an exception will be thrown.
                       - If the sale price is outside the allowed bounds, an exception will be thrown.
                    """)

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<ProductResponse> saleMode(@RequestBody SaleProductRequest saleProductRequest);

    @Operation(summary = "Create a new draft from a product",
            description = """
                    Returns the ID of the new draft product.
                                        
                    Process:
                       - Copy data from the product using `version_id`.
                       - Save a new draft product with the copied data.
                       - Copy photos from the product.
                       - Associate photos with the draft product.
                       - Return the ID of the draft product.
                                    
                    Rules:
                       - If the product is not found by `version_id`, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied"),
            @ApiResponse(responseCode = "404", description = "Product not found")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> toDraftProduct(@PathVariable("id") UUID versionId);

    @Operation(summary = "Create a new empty draft",
            description = """
                    Returns the `ID` of the new draft.
                                        
                    Description:
                       - Create a new draft without any data.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> addDraft();

    @Operation(summary = "Get a draft",
            description = """
                    Returns a draft product by `ID`.
                                        
                    Rules:
                       - If the draft product is not found by `ID`, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductResponse> getDraft(@PathVariable("draftId") Long id);

    @Operation(summary = "Get a list of draft `ID`s", description = "Returns a list of draft `ID`s")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<Long>> getDrafts();

    @Operation(summary = "Update a draft",
            description = """
                    Returns the updated draft.
                                        
                    Rules:
                       - If the draft product is not found by ID, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Invalid request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductResponse> updateDraftProduct(@RequestBody DraftProductRequest draftProductRequest);

    @Operation(summary = "Delete a draft",
            description = """
                    Returns the deleted draft product.
                                        
                    Rules:
                       - If the draft product is not found by ID, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<DraftProductResponse> deleteDraftProduct(@PathVariable("draftId") Long id);

    @Operation(summary = "Add new tag to the draft product by draftId",
            description = """
                    Returns the draftProductRequest with updated tags.

                    Process:
                       - Create a new tag by tagRequest.
                       - Match the new tag to draftProduct by `ID`.
                       - Return the draft product.

                    Rules:
                       - If the draft product is not found by `ID`, an exception will be thrown.
                       - If the tagRequest has invalid data, an exception will be thrown.
                       - If tag already exist, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TagResponse>> addNewTagToDraftProduct(@PathVariable("draftId") Long id, @RequestBody TagRequest tagRequest);

    @Operation(summary = "Add tag to the draft product by draftId",
            description = """
                    Returns the draftProductRequest with updated tags.

                    Process:
                       - Match the tag by id to draftProduct by draftId.
                       - Return the draft product.

                    Rules:
                       - If the draft product is not found by `ID`, an exception will be thrown.
                       - If tag already matched to the draft product, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TagResponse>> addTagToDraftProduct(@PathVariable("draftId") Long id, @RequestParam("id") Long tagId);

    @Operation(summary = "Get all tags")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TagResponse>> getTags();

    @Operation(summary = "Create a new tag",
            description = """
                    Returns the tagResponse.

                    Process:
                       - Create a new tag by tagRequest.
                       - Return the tag.

                    Rules:
                       - If the tagRequest has invalid data, an exception will be thrown.
                       - If tag already exist, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TagResponse> createTag(@RequestBody TagRequest tagRequest);

    @Operation(summary = "Delete the tag by id from draft product",
            description = """
                    Returns the draftProductRequest with updated tags.

                    Process:
                       - Delete the tag matching to draftId by `ID`.
                       - Return the tag.

                    Rules:
                       - If the tag is not found by `ID`, an exception will be thrown.
                       - If the draft product is not found by `ID`, an exception will be thrown.
                       - If the tag is not matched to the draft product, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TagResponse>> deleteTagFromDraft(@PathVariable("draftId") Long id, @RequestParam("id") Long tagId);

    @Operation(summary = "Delete the tag by id",
            description = """
                    Returns the tagResponse.

                    Process:
                       - Delete the tag by `ID`.
                       - Return the tag.

                    Rules:
                       - If the tag is not found by `ID`, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<TagResponse> deleteTag(@PathVariable("tagId") Long tagId);

    @Operation(summary = "Get tags by draftId",
            description = """
                    Returns list of TagResponse.
                    
                    Rules:
                       - If the draft product is not found by `ID`, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in request body"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<TagResponse>> getTagByDraft(@PathVariable("draftId") Long id);
}
