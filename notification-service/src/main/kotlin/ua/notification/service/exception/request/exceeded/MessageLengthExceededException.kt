package ua.notification.service.exception.request.exceeded

import ua.notification.service.exception.request.main.NotificationRequestException

@Suppress("unused")
class MessageLengthExceededException : NotificationRequestException {
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
        fun create(curLength: Int): MessageLengthExceededException {
            return MessageLengthExceededException("Message length for sms can't be more 160 characters, now length is $curLength")
        }
    }
}