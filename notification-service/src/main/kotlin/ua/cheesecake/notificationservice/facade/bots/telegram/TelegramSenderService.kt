package ua.cheesecake.notificationservice.facade.bots.telegram

import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.SendResult
import ua.cheesecake.notificationservice.facade.NotificationData
import ua.cheesecake.notificationservice.facade.Notifier
import ua.cheesecake.notificationservice.facade.NotifyType
import java.util.concurrent.CompletableFuture

class TelegramSenderService(
    private val kafkaTemplate: KafkaTemplate<String, NotificationData>
) : Notifier {
    @Value("\${kafka.topic.message}")
    private lateinit var kafkaTopicMessage: String

    override suspend fun notify(request: NotificationData): Notifier.NotificationStatus {
        return try {
            sendMessageToKafka(request)
            Notifier.NotificationStatus.SUCCESS
        } catch (e: Exception) {
            Notifier.NotificationStatus.ERROR
        }
    }

    private fun sendMessageToKafka(request: NotificationData): CompletableFuture<SendResult<String, NotificationData>> {
        return kafkaTemplate.send(kafkaTopicMessage, request)
    }

    override fun getNotificationType(): NotifyType = NotifyType.TELEGRAM
}
