package ua.notification.service.service

import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.dto.ProcessMetadataResponse
import ua.notification.service.entity.Task
import ua.notification.service.entity.TaskMetadata
import ua.notification.service.entity.additional.NotifyType
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.Tuple
import ua.notification.service.utils.builder.DaoBuilder
import ua.notification.service.utils.builder.EntityBuilder
import ua.notification.service.utils.request.TaskRequestConstructor
import ua.notification.service.utils.validator.RequestValidator

@Component
class NotificationService(
    private val validator: RequestValidator,
    private val entityBuilder: EntityBuilder,
    private val daoBuilder: DaoBuilder,
    private val taskRequestConstructor: TaskRequestConstructor,
    private val messageService: MessageService,
) {

    fun new(notification: NotificationRequest, type: NotifyType): NotificationResponse {
        validator.forceValidate(notification, type)
        val tuple: Tuple<Task, TaskMetadata> = entityBuilder.buildTaskTuple(notification, type, ProcessStatus.PENDING)
        val task: Task = taskRequestConstructor.saveTask(tuple)
        messageService.sendTask(tuple)
        return daoBuilder.buildNotificationResponse(task)
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
        return daoBuilder.buildProcessMetadataResponse(taskRequestConstructor.getProcessMetadata(id))
    }
}