package ua.notification.service.utils.builder

import org.springframework.stereotype.Component
import ua.notification.service.dto.DirectNotificationRequest
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.entity.*
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.entity.additional.NotifyType
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.Tuple
import ua.notification.service.entity.additional.notification.DirectNotification
import ua.notification.service.entity.additional.notification.Notification
import ua.notification.service.entity.additional.notification.NotificationMethod
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import java.util.*

@Component
class EntityBuilder {

    fun buildTask(status: ProcessStatus): Task {
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

    fun buildTaskTuple(notificationRequest: NotificationRequest, status: ProcessStatus): Tuple<Task, TaskMetadata> {
        return Tuple(
            this.buildTask(status),
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

    fun buildMessageTask(tuple: Tuple<Task, TaskMetadata>): MessageTask {
        return MessageTask(
            id = tuple.arg1.id!!,
            time = tuple.arg1.createTime,
            status = tuple.arg1.status,
            message = tuple.arg2.message,
            notifyType = tuple.arg2.notifyType
        )
    }

    fun buildProcessMetadata(task: Task): ProcessMetadata {
        return ProcessMetadata(
            task = task
        )
    }

    fun buildDirectTaskTuple(
        directNotification: DirectNotificationRequest,
        notifyType: NotifyType,
        status: ProcessStatus
    ): Tuple<DirectTask, DirectTaskMetadata> {
        return Tuple(
            this.buildDirectTask(status),
            this.buildDirectTaskMetadata(directNotification, notifyType)
        )
    }

    fun buildDirectTask(status: ProcessStatus): DirectTask {
        return DirectTask(
            id = null,
            metadata = null,
            status = status
        )
    }

    fun buildDirectTaskMetadata(
        directNotification: DirectNotificationRequest,
        notifyType: NotifyType
    ): DirectTaskMetadata {
        return DirectTaskMetadata(
            id = null,
            message = directNotification.message!!,
            notifyType = notifyType,
            userId = directNotification.userId!!
        )
    }

    fun buildDirectNotification(
        principal: NotificationPrincipal,
        method: NotificationMethod,
        directTask: DirectTask
    ): DirectNotification {
        return DirectNotification(
            directTaskId = directTask.id!!,
            uuid = UUID.randomUUID(),
            method = method,
            principal = principal,
            message = null
        )
    }
}