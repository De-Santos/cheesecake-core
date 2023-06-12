package ua.notification.service.utils.builder

import org.springframework.stereotype.Component
import ua.notification.service.dto.DirectNotificationResponse
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.dto.ProcessMetadataResponse
import ua.notification.service.entity.DirectTask
import ua.notification.service.entity.ProcessMetadata
import ua.notification.service.entity.Task
import java.util.*

@Component
class DaoBuilder {

    fun buildNotificationResponse(task: Task): NotificationResponse {
        return NotificationResponse(
            id = task.id!!,
            createTime = task.createTime,
            processStatus = task.status,
            processMetadata = task.processMetadata!!.id!!
        )
    }

    fun buildDirectNotificationResponse(directTask: DirectTask): DirectNotificationResponse {
        return DirectNotificationResponse(
            id = directTask.id!!,
            status = directTask.status,
            creationTime = directTask.creationTime,
            userId = directTask.metadata!!.userId
        )
    }

    fun buildProcessMetadataResponse(processMetadata: ProcessMetadata): ProcessMetadataResponse {
        return ProcessMetadataResponse(
            id = processMetadata.id!!,
            startTime = processMetadata.startTime,
            endTime = processMetadata.endTime,
            elapsedTime = this.calculateElapsedTime(processMetadata),
            userNotified = processMetadata.usersProcessed
        )
    }

    private fun calculateElapsedTime(pm: ProcessMetadata): Long? {
        if (pm.startTime == null) return null
        if (pm.endTime == null) return Date().time - pm.startTime.time
        return pm.endTime.time - pm.startTime.time
    }
}