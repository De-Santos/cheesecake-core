package ua.notification.service.entity.additional.notification

data class NotificationPrincipal(
    val userId: Long,
    val email: String?,
    val phone: String?,
    val username: String,
    val emailNotification: Boolean,
    val smsNotification: Boolean
)
