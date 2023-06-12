package ua.notification.service.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class DirectNotificationRequest(
    @JsonProperty("user_id")
    val userId: Long?,
    @JsonProperty("message")
    val message: String?,
)
