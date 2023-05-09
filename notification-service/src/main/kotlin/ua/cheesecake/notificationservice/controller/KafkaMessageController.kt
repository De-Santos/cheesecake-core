package ua.cheesecake.notificationservice.controller

import io.klogging.Klogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Controller
import ua.cheesecake.notificationservice.dto.MessageDto
import ua.cheesecake.notificationservice.dto.NotificationDto
import ua.cheesecake.notificationservice.service.MessageService

@Controller
class KafkaMessageController(
    private val messageService: MessageService
) {

    @KafkaListener(topics = ["\${kafka.topic.notification-service.message}"], groupId = "\${kafka.group.notification-service.message}")
    suspend fun saveMessage(message: MessageDto) {
        logger.info("Saving message: $message")
        messageService.saveMessage(message)
        logger.info("Message received: $message")
    }

    @KafkaListener(topics = ["\${kafka.topic.notification-service.notification}"], groupId = "\${kafka.group.notification-service.notification}")
    suspend fun saveMessages(notification: NotificationDto) {
        logger.info("Saving messages: $notification")
        messageService.saveMessages(notification)
        logger.info("Messages received: $notification")
    }

    companion object : Klogging
}
