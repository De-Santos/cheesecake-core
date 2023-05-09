package ua.cheesecake.notificationservice.dto

import jakarta.validation.constraints.NotNull
import ua.cheesecake.notificationservice.facade.NotifyType

class MessageDto(
    var id: Long? = null,
    @NotNull var message: String? = null,
    @NotNull var notifyType: NotifyType? = null,
    @NotNull var account: String? = null,
)
