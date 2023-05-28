package ua.notification.service.facade.email

import io.klogging.Klogging
import org.springframework.mail.MailException
import org.springframework.mail.MailSender
import org.springframework.stereotype.Service
import ua.notification.service.facade.NotificationData
import ua.notification.service.facade.Notifier
import ua.notification.service.facade.NotifyType
import ua.notification.service.utils.mapper.toSimpleMailMessage

@Service
class EmailSenderService(
    private val emailSender: MailSender,
) : Notifier {

    override fun notify(request: NotificationData): Notifier.NotificationStatus {
        val simpleMailMessage = request.toSimpleMailMessage()
        return try {
            emailSender.send(simpleMailMessage)
//            logger.info("Email sent: $simpleMailMessage")
            Notifier.NotificationStatus.SUCCESS
        } catch (e: MailException) {
//            logger.error("Email sending error: $simpleMailMessage", e)
            Notifier.NotificationStatus.ERROR
        }
    }

    override fun getNotificationType(): NotifyType = NotifyType.EMAIL

    companion object : Klogging
}
