package ua.cheesecake.notificationservice.service

import io.klogging.Klogging
import jakarta.annotation.PostConstruct
import jakarta.transaction.Transactional
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ua.cheesecake.notificationservice.dao.MessageRepository
import ua.cheesecake.notificationservice.domain.Message
import ua.cheesecake.notificationservice.domain.SendStatus
import ua.cheesecake.notificationservice.dto.MessageDto
import ua.cheesecake.notificationservice.dto.NotificationDto
import ua.cheesecake.notificationservice.facade.Notifier
import ua.cheesecake.notificationservice.facade.NotifyType
import ua.cheesecake.notificationservice.utils.mapper.toMessage
import ua.cheesecake.notificationservice.utils.mapper.toDto
import ua.cheesecake.notificationservice.utils.mapper.toSendStatus

@Service
class MessageService(
    private val messageRepository: MessageRepository,
    private val subscriptionService: SubscriptionService
) {
    private lateinit var notificationsMap: Map<NotifyType, Notifier>

    suspend fun saveMessage(messageDto: MessageDto) {
        val message = messageDto.toMessage()
        messageRepository.save(message)
    }

    @Transactional
    suspend fun saveMessages(notification: NotificationDto): List<MessageDto> {
        val messages = getMessagesForNotification(notification)
        return messageRepository.saveAll(messages).map { it.toDto() }
    }

    private suspend fun getMessagesForNotification(notification: NotificationDto): List<Message> {
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
    suspend fun sendMessages() {
        val messages: List<Message> = messageRepository.findAllUnsentMessages()
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
            throw IllegalStateException("Notifier not found: ${message.notifyType}")
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
