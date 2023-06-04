package ua.notification.service.service

import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.entity.Task
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.TaskTuple
import ua.notification.service.utils.broker.MessageBroker
import ua.notification.service.utils.builder.EntityBuilder
import ua.notification.service.utils.request.TaskRequestConstructor
import ua.notification.service.utils.validator.Validator

@Component
class NotificationService(
    private val validator: Validator,
    private val broker: MessageBroker,
    private val entityBuilder: EntityBuilder,
    private val taskRequestConstructor: TaskRequestConstructor,
) {

    fun new(notification: NotificationRequest): NotificationResponse {
        validator.forceValidate(notification)
        val tuple: TaskTuple = entityBuilder.buildTaskTuple(notification, ProcessStatus.PENDING)
        val task: Task = taskRequestConstructor.saveTask(tuple)
        broker.sendTask(entityBuilder.buildMessageTask(tuple))
        return entityBuilder.buildNotificationResponse(task)
    }

    fun info(id: Long): NotificationResponse {
        return NotificationResponse(
            id = id,
            processStatus = taskRequestConstructor.getInfoById(id)
        )
    }

    fun all(): List<Long> {
        return taskRequestConstructor.getAll()
    }

    fun active(): List<Long> {
        return taskRequestConstructor.getActive()
    }

    fun byStatus(status: ProcessStatus): List<Long> {
        return taskRequestConstructor.getAllByStatus(status)
    }
}