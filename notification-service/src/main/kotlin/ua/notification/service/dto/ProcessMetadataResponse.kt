package ua.notification.service.dto

import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

data class ProcessMetadataResponse(
    @JsonProperty("id")
    val id: Long,

    @JsonProperty("start_time")
    var startTime: Date? = null,

    @JsonProperty("end_time")
    var endTime: Date? = null,

    @JsonProperty("elapse_time")
    var elapsedTime: Long? = null,

    @JsonProperty("users_notified")
    var userNotified: Long? = null
)
