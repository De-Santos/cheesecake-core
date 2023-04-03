package com.file.receiver.controller.internal;

import com.file.receiver.dto.FileUseRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

public interface InternalApi {

    @Operation(summary = "Check is file in database", description = "Check is file in database, return bullean")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> fileExist(@PathVariable String id);

    @Operation(summary = "Set photo state in use", description = "Set photo state inUse: true")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
    })
    @ResponseStatus(HttpStatus.OK)
    void use(@RequestBody FileUseRequest fileUseRequest);

}
