package ua.notification.service.exception.internal.invalid

import ua.notification.service.exception.internal.main.InternalMethodException

@Suppress("unused")
class InvalidFullTaskSignatureException : InternalMethodException {
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
}