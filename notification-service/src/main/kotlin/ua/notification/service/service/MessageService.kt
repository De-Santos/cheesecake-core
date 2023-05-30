package ua.notification.service.service

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Service
import ua.notification.service.dao.MessageRepository
import ua.notification.service.domain.Message
import ua.notification.service.dto.MessageDto
import ua.notification.service.dto.NotificationDto
import ua.notification.service.utils.mapper.toMessage

@Service
class MessageService(
    private val rabbit: RabbitTemplate,
    private val queue: Queue,
    private val messageRepository: MessageRepository,
    private val subscriptionService: SubscriptionService,
) {
    private val log = LoggerFactory.getLogger(javaClass)


    fun saveMessage(messageDto: MessageDto): Message {
        val message = messageDto.toMessage()
        return messageRepository.save(message)
    }

    fun saveMessages(notification: NotificationDto) {
        val messages = notification.message
        log.info("Save massage in rabbit")
        return rabbit.convertAndSend(queue.name, messages)
    }


    fun sendMessages() {
        log.info("Sending messages")
        val messages: List<Message> = messageRepository.findAllUnsentMessages()
        log.info("Found ${messages.size} unsent messages")
        messages.forEach {
            sendMessage(it)
        }
        messageRepository.saveAll(messages)
        log.info("Messages sent")
    }

    private fun sendMessage(message: Message) {
//            logger.info("Message sent: $message, status: $notificationStatus")
    }
}
