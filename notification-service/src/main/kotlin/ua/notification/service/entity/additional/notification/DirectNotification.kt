package ua.notification.service.entity.additional.notification

import java.util.*

data class DirectNotification(
    val directTaskId: Long,
    val uuid: UUID,
    val method: NotificationMethod,
    val principal: NotificationPrincipal,
    val message: String?,
    val time: Date = Date(),
)
