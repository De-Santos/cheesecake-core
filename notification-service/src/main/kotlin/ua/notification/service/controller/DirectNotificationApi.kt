package ua.notification.service.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import ua.notification.service.dto.DirectNotificationRequest
import ua.notification.service.dto.DirectNotificationResponse
import ua.notification.service.entity.additional.NotifyType

interface DirectNotificationApi {

    @Operation(summary = "Create direct notification")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation,",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(responseCode = "400", description = "Invalid request."),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    fun createDirectNotification(@RequestParam type: NotifyType, @RequestBody notification: DirectNotificationRequest): ResponseEntity<DirectNotificationResponse>

    @Operation(summary = "Create force direct notification")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation,",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(responseCode = "400", description = "Invalid request."),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    fun createForceDirectNotification(@RequestParam type: NotifyType, @RequestBody notification: DirectNotificationRequest): ResponseEntity<DirectNotificationResponse>

    @Operation(summary = "Get direct notification by id")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation,",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(responseCode = "400", description = "Invalid request."),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    fun getDirectNotification(@PathVariable id: Long): ResponseEntity<DirectNotificationResponse>

    @Operation(
        summary = "Get all direct notifications",
        description = "Returns list of ids"
    )
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation,",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(responseCode = "400", description = "Invalid request."),
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    fun getAllDirectNotification(): ResponseEntity<List<Long>>
}