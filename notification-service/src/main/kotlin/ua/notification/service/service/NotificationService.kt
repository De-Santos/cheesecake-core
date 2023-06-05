package ua.notification.service.service

import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.dto.ProcessMetadataResponse
import ua.notification.service.entity.Task
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.TaskTuple
import ua.notification.service.utils.broker.MessageBroker
import ua.notification.service.utils.builder.EntityBuilder
import ua.notification.service.utils.builder.ResponseBuilder
import ua.notification.service.utils.request.TaskRequestConstructor
import ua.notification.service.utils.validator.Validator

@Component
class NotificationService(
    private val validator: Validator,
    private val broker: MessageBroker,
    private val entityBuilder: EntityBuilder,
    private val responseBuilder: ResponseBuilder,
    private val taskRequestConstructor: TaskRequestConstructor,
) {

    fun new(notification: NotificationRequest): NotificationResponse {
        validator.forceValidate(notification)
        val tuple: TaskTuple = entityBuilder.buildTaskTuple(notification, ProcessStatus.PENDING)
        val task: Task = taskRequestConstructor.saveTask(tuple)
        broker.sendTask(entityBuilder.buildMessageTask(tuple))
        return responseBuilder.buildNotificationResponse(task)
    }

    fun get(id: Long): NotificationResponse {
        return taskRequestConstructor.getNotificationById(id)
    }

    fun getStatus(id: Long): ProcessStatus {
        return taskRequestConstructor.getStatusById(id)
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

    fun processMetadata(id: Long): ProcessMetadataResponse {
        return responseBuilder.buildProcessMetadataResponse(taskRequestConstructor.getProcessMetadata(id))
    }
}