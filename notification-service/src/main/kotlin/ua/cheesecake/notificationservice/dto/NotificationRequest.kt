package ua.cheesecake.notificationservice.dto

import jakarta.validation.constraints.NotNull


class NotificationRequest(
    @NotNull var userId: Long? = null,
    @NotNull var message: String? = null,
    @NotNull var account: String? = null
)
