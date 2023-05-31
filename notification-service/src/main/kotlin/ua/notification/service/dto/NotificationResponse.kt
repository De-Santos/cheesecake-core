package ua.notification.service.dto

import ua.notification.service.entity.additional.ProcessStatus

data class NotificationResponse(
    val processStatus: ProcessStatus
)