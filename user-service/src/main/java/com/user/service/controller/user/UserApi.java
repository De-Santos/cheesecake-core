package com.user.service.controller.user;

import com.user.service.dto.user.UserRegistrationDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.constraints.NotNull;
import ua.cheesecake.dto.UserDto;

public interface UserApi {
    @Operation(summary = "Create new user", description = "Create user in database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation"),
            @ApiResponse(responseCode = "406", description = "Not acceptable email or password")
    })
    @ResponseStatus(HttpStatus.OK)
    ResponseEntity<UserDto> registration(@NotNull @RequestBody UserRegistrationDto userRegistrationDto);

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
