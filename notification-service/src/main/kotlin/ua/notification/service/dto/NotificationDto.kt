package ua.notification.service.dto

import jakarta.validation.constraints.NotNull


class NotificationDto(
    @NotNull var message: String,
)
