package ua.cheesecake.notificationservice.facade

interface Notifier {
    suspend fun notify(request: NotificationData): NotificationStatus

    enum class NotificationStatus {
        SUCCESS,
        ERROR,
    }

    fun getNotificationType(): NotifyType
}