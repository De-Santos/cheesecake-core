package ua.cheesecake.notificationservice.dto

import jakarta.validation.constraints.NotNull
import ua.cheesecake.notificationservice.facade.NotifyType

class SubscriptionDto(
    var id: Long?,
    @NotNull
    var userId: Long?,
    var messengers: List<NotifyType> = emptyList()
)
