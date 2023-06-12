package ua.notification.service.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ua.notification.service.entity.additional.ProcessStatus
import java.util.*

data class DirectNotificationResponse(
    @JsonProperty("id")
    val id: Long,
    @JsonProperty("user_id")
    val userId: Long,
    @JsonProperty("status")
    val status: ProcessStatus,
    @JsonProperty("creation_time")
    val creationTime: Date,
)
