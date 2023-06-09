package ua.notification.service.utils.request

import org.springframework.stereotype.Component
import ua.notification.service.dao.ProcessMetadataRepository
import ua.notification.service.dao.TaskMetadataRepository
import ua.notification.service.dao.TaskRepository
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.entity.ProcessMetadata
import ua.notification.service.entity.Task
import ua.notification.service.entity.TaskMetadata
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.Tuple
import ua.notification.service.exception.request.found.ProcessMetadataNotFoundException
import ua.notification.service.exception.request.found.TaskNotFoundException
import ua.notification.service.utils.builder.EntityBuilder
import ua.notification.service.utils.request.accelerator.JdbcAccelerator

@Component
class TaskRequestConstructor(
    private val taskRepository: TaskRepository,
    private val taskMetadataRepository: TaskMetadataRepository,
    private val processMetadataRepository: ProcessMetadataRepository,
    private val builder: EntityBuilder,
    private val accelerator: JdbcAccelerator
) {
    fun saveTask(tuple: Tuple<Task, TaskMetadata>): Task {
        val savedTask: Task = taskRepository.save(tuple.arg1)
        tuple.arg2.task = savedTask
        taskMetadataRepository.save(tuple.arg2)
        savedTask.processMetadata = processMetadataRepository.save(builder.buildProcessMetadata(savedTask))
        return savedTask
    }

    fun getStatusById(id: Long): ProcessStatus {
        return accelerator.getStatusById(id).orElseThrow {
            TaskNotFoundException("task not found by id: $id")
        }
    }

    fun getAll(): List<Long> {
        return accelerator.getAllTaskIds()
    }

    fun getActive(): List<Long> {
        return accelerator.getActiveIds()
    }

    fun getAllByStatus(status: ProcessStatus): List<Long> {
        return accelerator.getIdsByStatus(status)
    }

    fun getProcessMetadata(id: Long): ProcessMetadata {
        return accelerator.getProcessMetadataById(id).orElseThrow {
            ProcessMetadataNotFoundException("process medata not found by id: $id")
        }
    }

    fun getNotificationById(id: Long): NotificationResponse {
        return accelerator.getNotificationById(id).orElseThrow {
            TaskNotFoundException("task not found by id: $id")
        }
    }
}