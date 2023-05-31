package ua.notification.service.dto

import ua.notification.service.entity.additional.NotificationType

data class NotificationRequest(
    val type: NotificationType,
    val message: String?
)