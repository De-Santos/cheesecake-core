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

public interface PhotoApi {
    @Operation(summary = "Upload photo in database",
            description = "Upload selected photo in database with tagName and return photo id." +
                    "If tagName is null, default tagName is real file name. TagName can't contains emoji only text.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied `file` or in `tagName`."),
            @ApiResponse(responseCode = "406", description = "Supplied invalid `file` or `tagName` etc."),

    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile fileRequest);

    @Operation(summary = "Return photo by id", description = "Return photo by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<byte[]> get(@PathVariable String id);

    @Operation(summary = "Delete photo by id", description = "Delete photo from database by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<PhotoResponse> remove(@PathVariable String id);

}
