package com.file.receiver.filereceiver.controller;

import com.file.receiver.filereceiver.model.Photo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PhotoApi {
        @Operation(summary = "Upload photo in database", description = "Upload selected photo in database and return photo id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation"),
                        @ApiResponse(responseCode = "400", description = "Error in supplied file"),
        })
        @ResponseStatus(HttpStatus.OK)
        ResponseEntity<String> uploadFile(@RequestPart("file") MultipartFile file);

        @Operation(summary = "Return list of photos", description = "Return list of all photos in database")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation")
        })
        @ResponseStatus(HttpStatus.OK)
        ResponseEntity<List<Photo>> getAll();

        @Operation(summary = "Return photo by id", description = "Return photo by id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation"),
                        @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
        })
        @ResponseStatus(HttpStatus.OK)
        ResponseEntity<Photo> get(@PathVariable String id);

        @Operation(summary = "Chech is file in database", description = "Check is file in database, return bullean")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation"),
        })
        @ResponseStatus(HttpStatus.OK)
        ResponseEntity<Boolean> fileExist(@PathVariable String id);

        @Operation(summary = "Delete photo by id", description = "Delete photo from database by id")
        @ApiResponses(value = {
                        @ApiResponse(responseCode = "200", description = "Successful operation"),
                        @ApiResponse(responseCode = "400", description = "Error in supplied file id"),
        })
        @ResponseStatus(HttpStatus.OK)
        ResponseEntity<Void> remove(@PathVariable String id);

}
