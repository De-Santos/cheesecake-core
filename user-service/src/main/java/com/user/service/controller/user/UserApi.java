package com.user.service.controller.user;

import com.user.service.dto.user.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.cheesecake.dto.UserDto;

import java.util.List;

@SuppressWarnings("unused")
public interface UserApi {
    @Operation(summary = "Create new user", description = "Create user in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserResponse> registration(@RequestBody UserRegistrationRequest userRegistrationRequest);

    @Operation(summary = "Get user private data", description = "Return user private data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "User or private data does not exist"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserPrivateDataResponse> getUserPrivateData(@NotNull @PathVariable(name = "id") Long userId);

    @Operation(summary = "Get user private data", description = "Return user private data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "User or private data does not exist"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserInfoResponse> getUserInfo(@NotNull @PathVariable(name = "id") Long userId);

    @Operation(summary = "Update user private data", description = "Update user private data by supplied private data")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "User or private data does not exist"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserPrivateDataResponse> updateUserPrivateData(@RequestBody UserPrivateDataRequest userPrivateDataDto);

    @Operation(summary = "Update user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "User or private data does not exist"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserResponse> updateUser(@RequestBody UserRequest userRequest);

    @Operation(summary = "Get all users", description = "Return list of user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<List<UserDto>> getUsers();

    @Operation(summary = "Delete user", description = "Delete user by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "404", description = "Unknown userId")
    })
    @ResponseStatus(HttpStatus.OK)
    void deleteUser(@NotNull @PathVariable(name = "id") Long userId);
}
