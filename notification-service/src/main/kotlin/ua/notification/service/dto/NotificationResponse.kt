package ua.notification.service.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ua.notification.service.entity.additional.ProcessStatus

data class NotificationResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("process_status")
    val processStatus: ProcessStatus
)