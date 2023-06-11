package ua.notification.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class NotificationRequest(
    @JsonProperty("message")
    val message: String?
)