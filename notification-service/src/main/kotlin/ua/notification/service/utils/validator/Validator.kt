package ua.notification.service.utils.validator

import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.entity.additional.NotificationType
import ua.notification.service.exception.request.exceeded.MessageLengthExceededException
import ua.notification.service.exception.request.invalid.InvalidMessageException

@Component
class Validator {

    fun forceValidate(notificationRequest: NotificationRequest) {
        this.messageValidation(notificationRequest)
    }

    private fun messageValidation(nr: NotificationRequest) {
        if (nr.message.isNullOrBlank()) throw InvalidMessageException("Message must be not null or blank")
        if ((nr.type == NotificationType.ALL || nr.type == NotificationType.SMS) && nr.message.length >= 160)
            throw MessageLengthExceededException.create(nr.message.length)
    }
}