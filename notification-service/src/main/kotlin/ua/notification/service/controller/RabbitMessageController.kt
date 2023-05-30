package ua.notification.service.controller

import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ua.notification.service.dto.NotificationDto
import ua.notification.service.service.MessageService


@RestController
class RabbitMessageController(
    private val messageService: MessageService
) : RabbitMessageApi {
    private val logger = LoggerFactory.getLogger(javaClass)

    @PostMapping("/message")
    override fun saveMessages(notification: NotificationDto): ResponseEntity<String> {
        logger.info("Saving messages: $notification")
        messageService.saveMessages(notification)
        return ResponseEntity.ok("ok. done!")
    }
}
