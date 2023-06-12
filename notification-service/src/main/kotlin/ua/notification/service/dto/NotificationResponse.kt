package ua.notification.service.dto

import com.fasterxml.jackson.annotation.JsonProperty
import ua.notification.service.entity.additional.ProcessStatus
import java.util.*

data class NotificationResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("create_time")
    val createTime: Date,

    @JsonProperty("process_status")
    val processStatus: ProcessStatus,

    @JsonProperty("process_metadata_id")
    val processMetadata: Long
)