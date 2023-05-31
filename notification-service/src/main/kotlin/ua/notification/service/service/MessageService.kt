package ua.notification.service.service

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import ua.notification.service.dto.NotificationDto

@Service
class MessageService(
    private val rabbit: RabbitTemplate,
    private val queue: Queue,
) {
    private val log = LoggerFactory.getLogger(javaClass)


    fun saveMessages(notification: NotificationDto) {
        val messages = notification.message
        log.info("Save massage in rabbit")
        return rabbit.convertAndSend(queue.name, messages)
    }
}
