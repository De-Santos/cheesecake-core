package ua.notification.service.utils.builder

import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageBuilder
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.core.MessagePropertiesBuilder
import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.entity.Task
import ua.notification.service.entity.TaskMetadata
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.TaskTuple
import ua.notification.service.entity.additional.notification.Notification
import ua.notification.service.utils.broker.converter.TaskMessageConverter

@Component
class EntityBuilder(
    private val messageConverter: TaskMessageConverter
) {
    private val messageProperty: MessageProperties = MessagePropertiesBuilder.newInstance()
        .setContentType(MessageProperties.CONTENT_TYPE_JSON)
        .build()

    fun buildTask(notificationRequest: NotificationRequest, status: ProcessStatus): Task {
        return Task(
            id = null,
            metadata = null,
            status = status
        )
    }

    fun buildMetadata(notificationRequest: NotificationRequest): TaskMetadata {
        return TaskMetadata(
            message = notificationRequest.message!!,
            notifyType = notificationRequest.type
        )
    }

    fun buildTaskTuple(notificationRequest: NotificationRequest, status: ProcessStatus): TaskTuple {
        return TaskTuple(
            this.buildTask(notificationRequest, status),
            this.buildMetadata(notificationRequest)
        )
    }

    fun buildMessage(task: Task): Message {
        return MessageBuilder.withBody(messageConverter.toMessage(task, messageProperty).body)
            .andProperties(messageProperty)
            .build()
    }

    fun buildMessage(notification: Notification): Message {
        return MessageBuilder.withBody(messageConverter.toMessage(notification, messageProperty).body)
            .andProperties(messageProperty)
            .build()
    }
}