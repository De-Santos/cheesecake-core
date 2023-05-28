package ua.notification.service.controller

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import ua.notification.service.dto.MessageDto
import ua.notification.service.dto.NotificationDto
import ua.notification.service.service.MessageService


@RestController
class RabbitMessageController(
    private val messageService: MessageService
) {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/message")
    fun saveMessage(@RequestBody message: MessageDto) {
        logger.info("Saving message: $message")
        messageService.saveMessage(message)
        logger.info("Message saved: $message")
    }

    @PostMapping("/messages")
    fun saveMessages(notification: NotificationDto) {
        logger.info("Saving messages: $notification")
        messageService.saveMessages(notification)
        logger.info("Messages saved: $notification")
    }
}
