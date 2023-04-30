package ua.cheesecake.notificationservice.utils.mapper

import org.springframework.mail.SimpleMailMessage
import ua.cheesecake.notificationservice.domain.Message
import ua.cheesecake.notificationservice.domain.SendStatus
import ua.cheesecake.notificationservice.dto.MessageRequest
import ua.cheesecake.notificationservice.facade.NotificationData
import ua.cheesecake.notificationservice.facade.Notifier

fun MessageRequest.toMessage() = Message(
    message = this.message,
    notifyType = this.notifyType,
    account = this.account,
)

fun Message.toMessageRequest() = MessageRequest(
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