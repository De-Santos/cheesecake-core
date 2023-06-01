package ua.notification.service.dto

import ua.notification.service.entity.additional.NotifyType

data class NotificationRequest(
    val type: NotifyType,
    val message: String?
)