package ua.notification.service.entity.additional.notification

import ua.notification.service.config.Property
import ua.notification.service.exception.internal.implement.MethodNotImplementedThisNotificationMethodException

enum class NotificationMethod {
    EMAIL,
    SMS;

    fun toTag(): String {
        if (this == EMAIL) return Property.emailTag
        if (this == SMS) return Property.smsTag
        throw MethodNotImplementedThisNotificationMethodException("Unresolved case: $this")
    }
}