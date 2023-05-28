package ua.notification.service.dto

import jakarta.validation.constraints.NotNull
import ua.notification.service.facade.NotifyType


data class MessageDto(
    var id: Long? = null,
    @NotNull var message: String? = null,
    @NotNull var notifyType: NotifyType? = null,
    @NotNull var account: String? = null,
)
