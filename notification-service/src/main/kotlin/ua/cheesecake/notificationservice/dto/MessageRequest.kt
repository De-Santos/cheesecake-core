package ua.cheesecake.notificationservice.dto

import jakarta.validation.constraints.NotNull
import ua.cheesecake.notificationservice.facade.NotifyType

class MessageRequest(
    @NotNull val message: String? = null,
    @NotNull val notifyType: NotifyType? = null,
    @NotNull val account: String? = null,
)
