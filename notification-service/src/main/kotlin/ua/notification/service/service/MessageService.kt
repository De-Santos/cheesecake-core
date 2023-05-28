package ua.notification.service.service

import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ua.notification.service.dao.MessageRepository
import ua.notification.service.domain.Message
import ua.notification.service.domain.SendStatus
import ua.notification.service.dto.MessageDto
import ua.notification.service.dto.NotificationDto
import ua.notification.service.facade.Notifier
import ua.notification.service.facade.NotifyType
import ua.notification.service.utils.mapper.toDto
import ua.notification.service.utils.mapper.toMessage
import ua.notification.service.utils.mapper.toSendStatus

@Service
class MessageService(
    private val messageRepository: MessageRepository,
    private val subscriptionService: SubscriptionService,
    notifiers: List<Notifier>
) {
    private val notificationsMap: Map<NotifyType, Notifier>
    private val logger = LoggerFactory.getLogger(javaClass)

    init {
        notificationsMap = notifiers.associateBy { it.getNotificationType() }
    }

    fun saveMessage(messageDto: MessageDto): Message {
        val message = messageDto.toMessage()
        return messageRepository.save(message)
    }

    @Transactional
    fun saveMessages(notification: NotificationDto): List<Unit> {
        val messages = getMessagesForNotification(notification)
        return messageRepository.saveAll(messages).map { it.toDto() }
    }

    private fun getMessagesForNotification(notification: NotificationDto): List<Message> {
        val subscription = subscriptionService.findByUserId(notification.userId!!)
        return subscription.messengers.map { messenger ->
            Message(
                message = notification.message!!,
                account = notification.account!!,
                notifyType = messenger,
            )
        }
    }

    @Scheduled(fixedDelay = 1000 * 10)
    fun sendMessages() {
        logger.info("Sending messages")
        val messages: List<Message> = messageRepository.findAllUnsentMessages()
        logger.info("Found ${messages.size} unsent messages")
        messages.forEach {
            sendMessage(it)
        }
        messageRepository.saveAll(messages)
        logger.info("Messages sent")
    }

    private fun sendMessage(message: Message) {
        val notification = notificationsMap[message.notifyType]
        if (notification != null) {
            val notificationStatus = notification.notify(message)
            message.sendStatus = notificationStatus.toSendStatus()
//            logger.info("Message sent: $message, status: $notificationStatus")
        } else {
            message.sendStatus = SendStatus.ERROR
            throw IllegalStateException("Notifier not found: ${message.notifyType}")
        }
    }
}
