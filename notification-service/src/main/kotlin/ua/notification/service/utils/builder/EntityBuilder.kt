package ua.notification.service.utils.builder

import org.springframework.stereotype.Component
import ua.notification.service.dto.DirectNotificationRequest
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.entity.*
import ua.notification.service.entity.additional.*
import ua.notification.service.entity.additional.notification.Notification
import ua.notification.service.entity.additional.notification.NotificationMethod
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.entity.additional.notification.NotificationType
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

    fun buildMetadata(notificationRequest: NotificationRequest, type: NotifyType): TaskMetadata {
        return TaskMetadata(
            message = notificationRequest.message!!,
            notifyType = type
        )
    }

    fun buildTaskTuple(
        notificationRequest: NotificationRequest,
        type: NotifyType,
        status: ProcessStatus
    ): Tuple<Task, TaskMetadata> {
        return Tuple(
            this.buildTask(status),
            this.buildMetadata(notificationRequest, type)
        )
    }

    fun buildNotification(
        principal: NotificationPrincipal,
        method: NotificationMethod,
        task: MessageTask
    ): Notification {
        return Notification(
            id = task.id,
            uuid = UUID.randomUUID(),
            notificationType = NotificationType.FOR_ALL,
            method = method,
            principal = principal,
            message = task.message
        )
    }

    fun buildNotification(
        principal: NotificationPrincipal,
        method: NotificationMethod,
        task: DirectTask
    ): Notification {
        return Notification(
            id = task.id!!,
            uuid = UUID.randomUUID(),
            notificationType = NotificationType.DIRECT,
            method = method,
            principal = principal,
            message = task.metadata!!.message
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
        status: ProcessStatus,
        sendType: SendType
    ): Tuple<DirectTask, DirectTaskMetadata> {
        return Tuple(
            this.buildDirectTask(status, sendType),
            this.buildDirectTaskMetadata(directNotification, notifyType)
        )
    }

    fun buildDirectTask(status: ProcessStatus, sendType: SendType): DirectTask {
        return DirectTask(
            id = null,
            metadata = null,
            sendType = sendType,
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

}