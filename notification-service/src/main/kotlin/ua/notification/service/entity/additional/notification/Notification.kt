package ua.notification.service.entity.additional.notification

import java.util.*

data class Notification(
    val taskId: Long,
    val uuid: UUID,
    val method: NotificationMethod,
    val principal: NotificationPrincipal,
    val message: String?,
    val time: Date = Date(),
)