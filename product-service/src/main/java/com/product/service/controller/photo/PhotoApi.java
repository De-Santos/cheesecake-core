package com.product.service.controller.photo;

import com.product.service.dto.photo.PhotoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

@SuppressWarnings("unused")
public interface PhotoApi {
    @Operation(summary = "Upload a banner photo in database",
            description = """
                    Upload the selected photo in the database as the banner photo and match it to the draft product by ID.
                    
                    Some rules:
                       - If the photo is larger than 20 MB, an exception will be thrown.
                       - If the photo's type is not `jpeg` or `png`, an exception will be thrown.
                       - If the draft product with the given ID already has the maximum photo count, an exception will be thrown.
                       - If the draft product with the obtained ID is not found, an exception will be thrown.
                       - If the obtained photo cannot be decoded, an exception will be thrown.
                    """)

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation, returns photo's id."),
            @ApiResponse(responseCode = "400", description = "Error in supplied `file`."),
            @ApiResponse(responseCode = "406", description = "Supplied invalid `file`."),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> uploadBannerFile(@RequestParam("file") MultipartFile fileRequest,
                                          @RequestParam("draftId") Long draftId);

    @Operation(summary = "Upload a description photo in database",
            description = """
                    Upload the selected photo in the database as the description photo and match it to the draft product by ID.
                    
                    Some rules:
                       - If the photo is larger than 20 MB, an exception will be thrown.
                       - If the photo's type is not `jpeg` or `png`, an exception will be thrown.
                       - If the draft product with the given ID already has the maximum photo count, an exception will be thrown.
                       - If the draft product with the obtained ID is not found, an exception will be thrown.
                       - If the obtained photo cannot be decoded, an exception will be thrown.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation, returns photo's id."),
            @ApiResponse(responseCode = "400", description = "Error in supplied `file`."),
            @ApiResponse(responseCode = "406", description = "Supplied invalid `file`."),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> uploadDescriptionFile(@RequestParam("file") MultipartFile fileRequest,
                                               @RequestParam("draftId") Long draftId);

    @Operation(summary = "Insert a file at position",
            description = """
                    Insert and change old file to new file at position in banner photos list.
                    
                    Some rules:
                       - If photo is larger than 20 MB, throws an exception.
                       - If photo's type is not `jpeg` or `png`, throws an exception.
                       - If draft product by ID already has the maximum photo count, throws an exception.
                       - If draft product by the obtained ID is not found, throws an exception.
                       - If obtained photo can't be decoded, throws an exception.
                       - If position is greater than the maximum photos count, throws an exception.
                       - If position is greater than the length of the banner photos list, the file is uploaded in the last position + 1.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation, returns photo's id."),
            @ApiResponse(responseCode = "400", description = "Error in supplied `file`."),
            @ApiResponse(responseCode = "406", description = "Supplied invalid `file`."),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Long> insertUploadFile(@RequestParam("file") MultipartFile fileRequest,
                                          @RequestParam("draftId") Long draftId,
                                          @RequestParam("position") Integer position);

    @Operation(summary = "Get the banner photo by id",
            description = "Returns photo by id, if photo doesn't found throws exception")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<byte[]> getBannerPhoto(@PathVariable("id") Long id);

    @Operation(summary = "Get the description photo by id",
            description = "Returns photo by id, if photo doesn't found throws exception")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<byte[]> getDescriptionPhoto(@PathVariable("id") Long id);

    @Operation(summary = "Delete the banner photo by id",
            description = """
                    Delete a photo from the database by its ID.
                    
                    Some rules:
                       - If the photo is not found, an exception is thrown.
                       - If the photo is successfully deleted, the backend will automatically normalize positions.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PhotoResponse> removeBannerPhoto(@RequestParam("id") Long id,
                                                    @RequestParam("draftId") Long draftId);

    @Operation(summary = "Delete the description photo by id",
            description = """
                    Delete a photo from the database by its ID.
                    
                    Some rules:
                       - If the photo is not found, an exception is thrown.
                       - If the photo is successfully deleted, the backend will automatically normalize positions.
                    """)
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PhotoResponse> removeDescriptionPhoto(@RequestParam("id") Long id,
                                                         @RequestParam("draftId") Long draftId);

}
