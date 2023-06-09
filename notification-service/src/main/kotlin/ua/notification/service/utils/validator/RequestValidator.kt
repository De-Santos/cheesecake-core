package ua.notification.service.utils.validator

import org.springframework.stereotype.Component
import ua.notification.service.dto.DirectNotificationRequest
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.entity.additional.NotifyType
import ua.notification.service.exception.request.exceeded.MessageLengthExceededException
import ua.notification.service.exception.request.found.UserNotFoundException
import ua.notification.service.exception.request.invalid.InvalidMessageException
import ua.notification.service.exception.request.invalid.InvalidUserIdException
import ua.notification.service.utils.request.accelerator.JdbcAccelerator

@Component
class RequestValidator(
    private val accelerator: JdbcAccelerator
) {

    fun forceValidate(notificationRequest: NotificationRequest) {
        this.messageValidate(notificationRequest.type, notificationRequest.message)
    }

    fun forceValidate(type: NotifyType, directNotificationRequest: DirectNotificationRequest) {
        this.messageValidate(type, directNotificationRequest.message)
        this.forceUserValidate(directNotificationRequest.userId)
    }

    private fun messageValidate(type: NotifyType, message: String?) {
        if (message.isNullOrBlank()) throw InvalidMessageException("Message must be not null or blank")
        if ((type == NotifyType.ALL || type == NotifyType.SMS) && message.length >= 160)
            throw MessageLengthExceededException.create(message.length)
    }

    private fun forceUserValidate(id: Long?) {
        if (id == null) throw InvalidUserIdException("User id can't be null")
        if (accelerator.existUserById(id)) return
        throw UserNotFoundException("User not found by id: $id")
    }
}