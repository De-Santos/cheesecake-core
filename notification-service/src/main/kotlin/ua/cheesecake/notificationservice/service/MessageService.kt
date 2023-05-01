package ua.cheesecake.notificationservice.service

import io.klogging.Klogging
import jakarta.annotation.PostConstruct
import jakarta.transaction.Transactional
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ua.cheesecake.notificationservice.dao.MessageRepository
import ua.cheesecake.notificationservice.dao.SubscriptionRepository
import ua.cheesecake.notificationservice.domain.Message
import ua.cheesecake.notificationservice.domain.SendStatus
import ua.cheesecake.notificationservice.dto.MessageRequest
import ua.cheesecake.notificationservice.dto.NotificationRequest
import ua.cheesecake.notificationservice.facade.Notifier
import ua.cheesecake.notificationservice.facade.NotifyType
import ua.cheesecake.notificationservice.utils.mapper.toMessage
import ua.cheesecake.notificationservice.utils.mapper.toSendStatus

@Service
class MessageService(
    private val messageRepository: MessageRepository,
    private val subscriptionRepository: SubscriptionRepository
) {
    private lateinit var notificationsMap: Map<NotifyType, Notifier>

    suspend fun saveMessage(messageRequest: MessageRequest) {
        val message = messageRequest.toMessage()
        messageRepository.save(message)
        logger.info("Message saved: $message")
    }

    @Transactional
    suspend fun saveMessages(notification: NotificationRequest) {
        logger.info("Saving messages: $notification")
        val messages = getMessagesForNotification(notification)
        messageRepository.saveAll(messages)
        logger.info("Messages saved: $notification")
    }

    private fun getMessagesForNotification(notification: NotificationRequest): MutableList<Message> {
        val subscription = subscriptionRepository.findByUserId(notification.userId!!)
        val messages = mutableListOf<Message>()
        subscription.messengers.forEach { messenger ->
            val message = Message(
                message = notification.message!!,
                account = notification.account!!,
                notifyType = messenger,
            )
            messages.add(message)
        }
        return messages
    }

    @Scheduled(fixedDelay = 1000 * 10)
    suspend fun sendMessages() {
        val messages: List<Message> = messageRepository.findAllNotSent()
        messages.forEach {
            sendMessage(it)
        }
        messageRepository.saveAll(messages)
    }

    private suspend fun sendMessage(message: Message) {
        val notification = notificationsMap[message.notifyType]
        if (notification != null) {
            val notificationStatus = notification.notify(message)
            message.sendStatus = notificationStatus.toSendStatus()
            logger.info("Message sent: $message, status: $notificationStatus")
        } else {
            message.sendStatus = SendStatus.ERROR
            logger.error("Message not sent: $message, status: ${message.sendStatus}")
        }
    }

    @PostConstruct
    fun init(notifiers: List<Notifier>) {
        notificationsMap = notifiers.associateBy {
            it.getNotificationType()
        }
    }

    companion object : Klogging
}
