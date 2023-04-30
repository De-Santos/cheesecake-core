package ua.cheesecake.notificationservice.facade.email

import io.klogging.Klogging
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.stereotype.Service
import ua.cheesecake.notificationservice.facade.NotificationData
import ua.cheesecake.notificationservice.facade.Notifier
import ua.cheesecake.notificationservice.facade.Notifier.NotificationStatus
import ua.cheesecake.notificationservice.facade.NotifyType
import ua.cheesecake.notificationservice.utils.mapper.toSimpleMailMessage

@Service
class EmailSenderService(
    private val emailSender: MailSender,
) : Notifier {

    override suspend fun notify(request: NotificationData): NotificationStatus {
        val simpleMailMessage = request.toSimpleMailMessage()
        return try {
            emailSender.send(simpleMailMessage)
            logger.info("Email sent: $simpleMailMessage")
            NotificationStatus.SUCCESS
        } catch (e: MailException) {
            logger.error("Email sending error: $simpleMailMessage", e)
            NotificationStatus.ERROR
        }
    }

    override fun getNotificationType(): NotifyType = NotifyType.EMAIL

    companion object : Klogging
}
