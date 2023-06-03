package ua.notification.service.utils.builder

import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.entity.Task
import ua.notification.service.entity.TaskMetadata
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.TaskTuple
import ua.notification.service.entity.additional.notification.Notification
import ua.notification.service.entity.additional.notification.NotificationMethod
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.utils.broker.converter.MessageTaskConverter
import java.util.*

@Component
class EntityBuilder(
    private val messageConverter: MessageTaskConverter
) {
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

    fun buildNotification(
        principal: NotificationPrincipal,
        method: NotificationMethod,
        task: MessageTask
    ): Notification {
        return Notification(
            taskId = task.id,
            uuid = UUID.randomUUID(),
            method = method,
            principal = principal,
            message = null
        )
    }

    fun buildMessageTask(tuple: TaskTuple): MessageTask {
        return MessageTask(
            id = tuple.task.id!!,
            time = tuple.task.time,
            status = tuple.task.status,
            message = tuple.taskMetadata.message,
            notifyType = tuple.taskMetadata.notifyType
        )
    }

}