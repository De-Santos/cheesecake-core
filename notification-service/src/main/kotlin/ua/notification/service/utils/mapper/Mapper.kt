package ua.notification.service.utils.mapper


import ua.notification.service.domain.Message
import ua.notification.service.domain.Subscription
import ua.notification.service.dto.MessageDto
import ua.notification.service.dto.SubscriptionDto


fun MessageDto.toMessage() = Message(
    notifyType = this.notifyType!!,
)

fun Message.toDto() = MessageDto(
    notifyType = this.notifyType,
)


fun Subscription.toDto(): SubscriptionDto = SubscriptionDto(
    id = id,
    userId = userId,
    messengers = messengers
)

fun SubscriptionDto.toSubscription(): Subscription = Subscription(
    id = id,
    userId = userId!!,
    messengers = messengers
)
