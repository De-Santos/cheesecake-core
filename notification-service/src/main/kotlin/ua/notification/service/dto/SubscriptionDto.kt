package ua.notification.service.dto

import jakarta.validation.constraints.NotNull
import ua.notification.service.facade.NotifyType


class SubscriptionDto(
    var id: Long?,
    @NotNull
    var userId: Long?,
    var messengers: List<NotifyType> = emptyList()
)
