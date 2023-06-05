package ua.notification.service.utils.builder

import org.springframework.stereotype.Component
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.dto.ProcessMetadataResponse
import ua.notification.service.entity.ProcessMetadata
import ua.notification.service.entity.Task

@Component
class ResponseBuilder {

    fun buildNotificationResponse(task: Task): NotificationResponse {
        return NotificationResponse(
            id = task.id!!,
            createTime = task.createTime,
            processStatus = task.status,
            processMetadata = task.processMetadata!!.id!!
        )
    }

    fun buildProcessMetadataResponse(processMetadata: ProcessMetadata): ProcessMetadataResponse {
        return ProcessMetadataResponse(
            id = processMetadata.id!!,
            startTime = processMetadata.startTime,
            endTime = processMetadata.endTime,
            elapsedTime = processMetadata.elapsedTime
        )
    }

}