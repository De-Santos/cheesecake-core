package ua.notification.service.facade.bots.telegram

import org.springframework.stereotype.Service
import ua.notification.service.facade.NotificationData
import ua.notification.service.facade.Notifier
import ua.notification.service.facade.NotifyType

@Service
class TelegramSenderService(
//    private val rabbitSender: RabbitSender
) : Notifier {

    override fun notify(request: NotificationData): Notifier.NotificationStatus {
        return try {
            sendMessageToRabbit(request)
            Notifier.NotificationStatus.SUCCESS
        } catch (e: Exception) {
            Notifier.NotificationStatus.ERROR
        }
    }

    fun sendMessageToRabbit(request: NotificationData) {
//        rabbitSender.send(request)
    }

    override fun getNotificationType(): NotifyType = NotifyType.TELEGRAM
}