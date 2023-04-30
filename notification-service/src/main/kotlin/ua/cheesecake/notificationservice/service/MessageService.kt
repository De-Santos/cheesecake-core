package ua.cheesecake.notificationservice.service

import io.klogging.Klogging
import jakarta.annotation.PostConstruct
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import ua.cheesecake.notificationservice.dao.MessageRepository
import ua.cheesecake.notificationservice.domain.Message
import ua.cheesecake.notificationservice.domain.SendStatus
import ua.cheesecake.notificationservice.dto.MessageRequest
import ua.cheesecake.notificationservice.facade.Notifier
import ua.cheesecake.notificationservice.facade.NotifyType
import ua.cheesecake.notificationservice.utils.mapper.toMessage
import ua.cheesecake.notificationservice.utils.mapper.toSendStatus

@Service
class MessageService(
    private val messageRepository: MessageRepository,
) {
    private lateinit var notificationsMap: Map<NotifyType, Notifier>

    suspend fun saveMessage(messageRequest: MessageRequest) {
        val message = messageRequest.toMessage()
        messageRepository.save(message)
        logger.info("Message saved: $message")
    }

    @Scheduled(fixedDelay = 1000 * 10)
    suspend fun sendMessages() {
        val messages: List<Message> = messageRepository.findAllNotSent()
        messages.forEach { message ->
            val notification = notificationsMap[message.notifyType]
            if (notification != null) {
                val notificationStatus = notification.notify(message)
                message.sendStatus = notificationStatus.toSendStatus()
                logger.info("Message sent: $message, status: $notificationStatus")
            } else {
                logger.error("Notification not found for type: ${message.notifyType}")
                message.sendStatus = SendStatus.ERROR
            }
        }
        messageRepository.saveAll(messages)
    }

    @PostConstruct
    fun init(notifiers: List<Notifier>) {
        notificationsMap = notifiers.associateBy {
            it.getNotificationType()
        }
    }

    companion object : Klogging
}
