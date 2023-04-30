package ua.cheesecake.notificationservice.dto

import jakarta.validation.constraints.NotNull
import ua.cheesecake.notificationservice.facade.NotifyType

class MessageRequest(
    @NotNull val message: String,
    @NotNull val notifyType: NotifyType,
    @NotNull val account: String,
)
