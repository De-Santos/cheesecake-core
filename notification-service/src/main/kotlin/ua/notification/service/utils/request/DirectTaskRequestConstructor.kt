package ua.notification.service.utils.request

import org.springframework.stereotype.Component
import ua.notification.service.dao.DirectTaskMetadataRepository
import ua.notification.service.dao.DirectTaskRepository
import ua.notification.service.dto.DirectNotificationResponse
import ua.notification.service.entity.DirectTask
import ua.notification.service.entity.DirectTaskMetadata
import ua.notification.service.entity.additional.Tuple
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.exception.request.found.DirectTaskNotFoundException
import ua.notification.service.exception.request.found.UserNotFoundException
import ua.notification.service.utils.request.accelerator.JdbcAccelerator

@Component
class DirectTaskRequestConstructor(
    private val directTaskRepository: DirectTaskRepository,
    private val directTaskMetadataRepository: DirectTaskMetadataRepository,
    private val accelerator: JdbcAccelerator
) {

    fun saveDirectTask(tuple: Tuple<DirectTask, DirectTaskMetadata>): DirectTask {
        val savedTask: DirectTask = directTaskRepository.save(tuple.arg1)
        tuple.arg2.directTask = savedTask
        savedTask.metadata = directTaskMetadataRepository.save(tuple.arg2)
        return savedTask
    }

    fun getPrincipalByUserId(id: Long): NotificationPrincipal {
        return accelerator.getNotificationPrincipalByUserId(id)
            .orElseThrow { UserNotFoundException.create(id) }
    }

    fun saveDirectTaskAndReturn(tuple: Tuple<DirectTask, DirectTaskMetadata>): Tuple<DirectTask, NotificationPrincipal> {
        return Tuple(
            arg1 = this.saveDirectTask(tuple),
            arg2 = this.getPrincipalByUserId(tuple.arg2.userId)
        )
    }

    fun getNotificationById(id: Long): DirectNotificationResponse {
        return accelerator.getDirectNotificationById(id).orElseThrow {
            DirectTaskNotFoundException("Direct task not found by id: $id")
        }
    }

    fun getAll(): List<Long> {
        return accelerator.getAllDirectTaskIds()
    }
}