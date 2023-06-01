package ua.notification.service.utils.broker.builder

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.stereotype.Component
import ua.notification.service.entity.Task
import ua.notification.service.entity.additional.notification.Notification
import ua.notification.service.utils.broker.converter.NotificationMessageConverter
import ua.notification.service.utils.broker.converter.TaskMessageConverter

@Component
class MessageBuilder(
    private val taskMessageConverter: TaskMessageConverter,
    private val notificationMessageConverter: NotificationMessageConverter,
) {
    private val messageProperty: MessageProperties = MessagePropertiesBuilder.newInstance()
        .setContentType(MessageProperties.CONTENT_TYPE_JSON)
        .build()


    fun build(task: Task): Message {
        return MessageBuilder.withBody(taskMessageConverter.toMessage(task, messageProperty).body)
            .andProperties(messageProperty)
            .build()
    }

    fun build(notification: Notification): Message {
        return MessageBuilder.withBody(notificationMessageConverter.toMessage(notification, messageProperty).body)
            .andProperties(messageProperty)
            .build()
    }
}