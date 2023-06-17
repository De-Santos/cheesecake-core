package ua.notification.service.entity.additional.notification

import java.util.*

data class Notification(
    val id: Long,
    val uuid: UUID,
    val notificationType: NotificationType,
    val method: NotificationMethod,
    val principal: NotificationPrincipal,
    val message: String?,
    val time: Date = Date(),
)