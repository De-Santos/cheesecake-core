package ua.notification.service.service

import org.springframework.stereotype.Component
import ua.notification.service.dto.DirectNotificationRequest
import ua.notification.service.dto.DirectNotificationResponse
import ua.notification.service.entity.DirectTask
import ua.notification.service.entity.DirectTaskMetadata
import ua.notification.service.entity.additional.*
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.utils.builder.DaoBuilder
import ua.notification.service.utils.builder.EntityBuilder
import ua.notification.service.utils.request.DirectTaskRequestConstructor
import ua.notification.service.utils.validator.RequestValidator

@Component
class DirectNotificationService(
    private val validator: RequestValidator,
    private val entityBuilder: EntityBuilder,
    private val daoBuilder: DaoBuilder,
    private val directTaskRequestConstructor: DirectTaskRequestConstructor,
    private val messageService: MessageService,
) {

    fun new(type: NotifyType, directNotification: DirectNotificationRequest, sendType: SendType): DirectNotificationResponse {
        validator.forceValidate(type, directNotification)
        val directTuple: Tuple<DirectTask, DirectTaskMetadata> = entityBuilder.buildDirectTaskTuple(directNotification, type, ProcessStatus.DONE, sendType)
        val messageTuple: Tuple<DirectTask, NotificationPrincipal> = directTaskRequestConstructor.saveDirectTaskAndReturn(directTuple)
        messageService.sendDirectTask(messageTuple, sendType)
        return daoBuilder.buildDirectNotificationResponse(directTuple.arg1)
    }

    fun get(id: Long): DirectNotificationResponse {
        return directTaskRequestConstructor.getNotificationById(id)
    }

    fun all(): List<Long> {
        return directTaskRequestConstructor.getAll()
    }
}