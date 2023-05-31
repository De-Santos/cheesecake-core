package ua.notification.service.utils.request

import org.springframework.stereotype.Component
import ua.notification.service.dao.TaskMetadataRepository
import ua.notification.service.dao.TaskRepository
import ua.notification.service.entity.additional.Task
import ua.notification.service.entity.additional.TaskTuple

@Component
class TaskRequestConstructor(
    private val taskRepository: TaskRepository,
    private val taskMetadataRepository: TaskMetadataRepository,
) {
    fun saveFullTask(taskTuple: TaskTuple): Task {
        val savedTask: Task = taskRepository.save(taskTuple.task)
        taskTuple.taskMetadata.task = savedTask
        taskMetadataRepository.save(taskTuple.taskMetadata)
        return savedTask
    }
}