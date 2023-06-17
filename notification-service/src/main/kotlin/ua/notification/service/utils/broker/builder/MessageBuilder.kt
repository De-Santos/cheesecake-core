package ua.notification.service.utils.broker.builder

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.stereotype.Component
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.entity.additional.notification.Notification
import ua.notification.service.utils.broker.converter.MessageTaskConverter
import ua.notification.service.utils.broker.converter.NotificationMessageConverter

@Component
class MessageBuilder(
    private val messageTaskConverter: MessageTaskConverter,
    private val notificationMessageConverter: NotificationMessageConverter,
) {
    private val messageProperty: MessageProperties = MessagePropertiesBuilder.newInstance()
        .setContentType(MessageProperties.CONTENT_TYPE_JSON)
        .build()

    fun build(messageTask: MessageTask): Message {
        println(messageTask.message)
        return MessageBuilder.withBody(messageTaskConverter.toMessage(messageTask, messageProperty).body)
            .andProperties(messageProperty)
            .build()
    }

    fun build(notification: Notification): Message {
        return MessageBuilder.withBody(notificationMessageConverter.toMessage(notification, messageProperty).body)
            .andProperties(messageProperty)
            .build()
    }
}