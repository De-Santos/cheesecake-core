package ua.cheesecake.notificationservice

import io.klogging.Klogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import ua.cheesecake.notificationservice.dto.MessageRequest
import ua.cheesecake.notificationservice.dto.NotificationRequest
import ua.cheesecake.notificationservice.service.MessageService

@Controller
class KafkaController(
    private val messageService: MessageService
) {

    @KafkaListener(topics = ["\${kafka.topic.notification}"], groupId = "\${kafka.group.id.notification}")
    suspend fun saveMessage(message: MessageRequest) {
        messageService.saveMessage(message)
        logger.info("Message received: $message")
    }

    @KafkaListener(topics = ["\${kafka.topic.notification}"], groupId = "\${kafka.group.id.notification}")
    suspend fun saveMessages(notification: NotificationRequest) {
        messageService.saveMessages(notification)
        logger.info("Messages received: $notification")
    }

    companion object : Klogging
}
