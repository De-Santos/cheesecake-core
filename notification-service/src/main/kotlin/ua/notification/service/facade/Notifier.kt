package ua.notification.service.facade

interface Notifier {
    fun notify(request: NotificationData): NotificationStatus

    enum class NotificationStatus {
        SUCCESS,
        ERROR,
    }

    fun getNotificationType(): NotifyType
}
