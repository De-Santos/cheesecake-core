package com.file.receiver.filereceiver.controller.internal;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

public interface InternalApi {
    
    @Operation(summary = "Check is file in database", description = "Check is file in database, return bullean")
    @ApiResponses(value = {
                    @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<Boolean> fileExist(@PathVariable String id);
    
}
