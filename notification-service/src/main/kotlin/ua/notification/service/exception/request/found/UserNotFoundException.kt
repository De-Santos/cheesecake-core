package ua.notification.service.exception.request.found

import ua.notification.service.exception.request.main.DirectNotificationException

@Suppress("unused")
class UserNotFoundException : DirectNotificationException {
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
        fun create(id: Long): UserNotFoundException {
            return UserNotFoundException("User not found by id: $id")
        }
    }
}