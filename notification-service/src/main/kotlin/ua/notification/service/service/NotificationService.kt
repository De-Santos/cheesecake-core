package ua.notification.service.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationRequest
import ua.notification.service.dto.NotificationResponse
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
    private val log = LoggerFactory.getLogger(javaClass)

    fun createNew(notification: NotificationRequest): NotificationResponse {
        log.info("Create new notification")
        validator.forceValidate(notification)
        val tuple: TaskTuple = entityBuilder.buildTaskTuple(notification, ProcessStatus.PENDING)
        taskRequestConstructor.saveFullTask(tuple)
        broker.sendTask(entityBuilder.buildMessageTask(tuple))
        return NotificationResponse(ProcessStatus.PENDING)
    }
}