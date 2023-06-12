package ua.notification.service.entity.additional

import java.util.*

data class MessageTask(
    val id: Long,
    val time: Date,
    val status: ProcessStatus,
    val message: String,
    val notifyType: NotifyType,
)
