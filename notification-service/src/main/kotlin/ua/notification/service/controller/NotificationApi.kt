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
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.dto.ProcessMetadataResponse
import ua.notification.service.entity.additional.ProcessStatus

interface NotificationApi {

    @Operation(summary = "Create notification")
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
    fun createNotification(@RequestBody notification: NotificationRequest): ResponseEntity<NotificationResponse>

    @Operation(summary = "Get notification by id")
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
    fun getNotification(@PathVariable id: Long): ResponseEntity<NotificationResponse>

    @Operation(summary = "Get notification status by id")
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
    fun getStatus(@PathVariable id: Long): ResponseEntity<ProcessStatus>

    @Operation(summary = "Get notification ids")
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
    fun getAll(): ResponseEntity<List<Long>>

    @Operation(
        summary = "Get active notification ids",
        description = """
Returns id of notification with `process_status`:
   - IN_PROCESS
   - PENDING
            """
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
    fun getActive(): ResponseEntity<List<Long>>

    @Operation(
        summary = "Get notification ids by status",
        description = "Returns ids of notification with required status."
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
    fun getAllByStatus(@RequestParam status: ProcessStatus): ResponseEntity<List<Long>>

    @Operation(summary = "Get process metadata by id")
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
    fun getProcessMetadata(@PathVariable id: Long): ResponseEntity<ProcessMetadataResponse>
}
