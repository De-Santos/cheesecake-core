package ua.notification.service.exception.request.disabled

import ua.notification.service.entity.additional.NotifyType
import ua.notification.service.exception.request.main.DirectNotificationException

@Suppress("unused")
class UserDissembledNotificationTypeException : DirectNotificationException {
    constructor() : super()
    constructor(message: String?) : super(message)
    constructor(message: String?, cause: Throwable?) : super(message, cause)
    constructor(cause: Throwable?) : super(cause)
    constructor(message: String?, cause: Throwable?, enableSuppression: Boolean, writableStackTrace: Boolean) : super(
        message,
        cause,
        enableSuppression,
        writableStackTrace
    )

    companion object {
        fun create(id: Long, type: NotifyType): UserDissembledNotificationTypeException {
            return UserDissembledNotificationTypeException("User by id: $id disable this notification type: $type")
        }
    }
}