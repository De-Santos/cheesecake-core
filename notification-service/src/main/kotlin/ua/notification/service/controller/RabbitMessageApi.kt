package ua.notification.service.controller

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus
import ua.notification.service.dto.NotificationDto

interface RabbitMessageApi {
    @Operation(summary = "save message in queue")
    @ApiResponses(
        value = [
            ApiResponse(
                responseCode = "200", description = "Successful operation,",
                content = [Content(mediaType = "application/json")]
            ),
            ApiResponse(responseCode = "400", description = "Error in supplied `file`."),
            ApiResponse(responseCode = "406", description = "Supplied invalid `file`.")
        ]
    )
    @ResponseStatus(HttpStatus.OK)
    fun saveMessages(@RequestBody notification: NotificationDto): ResponseEntity<String>
}
