package ua.cheesecake.notificationservice.utils.mapper

import org.springframework.mail.SimpleMailMessage
import ua.cheesecake.notificationservice.domain.Message
import ua.cheesecake.notificationservice.domain.SendStatus
import ua.cheesecake.notificationservice.domain.Subscription
import ua.cheesecake.notificationservice.dto.SubscriptionDto
import ua.cheesecake.notificationservice.dto.MessageDto
import ua.cheesecake.notificationservice.facade.NotificationData
import ua.cheesecake.notificationservice.facade.Notifier

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
