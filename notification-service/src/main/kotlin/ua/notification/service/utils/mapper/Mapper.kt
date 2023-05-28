package ua.notification.service.utils.mapper

import org.springframework.mail.SimpleMailMessage
import ua.notification.service.domain.Message
import ua.notification.service.domain.SendStatus
import ua.notification.service.domain.Subscription
import ua.notification.service.dto.MessageDto
import ua.notification.service.dto.SubscriptionDto
import ua.notification.service.facade.NotificationData
import ua.notification.service.facade.Notifier


fun MessageDto.toMessage() = Message(
    message = this.message!!,
    notifyType = this.notifyType!!,
    account = this.account!!,
)

fun Message.toDto() = MessageDto(
    message = this.message,
    notifyType = this.notifyType,
    account = this.account,
)

fun NotificationData.toSimpleMailMessage() = SimpleMailMessage().apply {
    setTo(this@toSimpleMailMessage.account)
    text = this@toSimpleMailMessage.message
}


fun Notifier.NotificationStatus.toSendStatus(): SendStatus = when (this) {
    Notifier.NotificationStatus.SUCCESS -> SendStatus.COMPLETED
    Notifier.NotificationStatus.ERROR -> SendStatus.ERROR
}

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
