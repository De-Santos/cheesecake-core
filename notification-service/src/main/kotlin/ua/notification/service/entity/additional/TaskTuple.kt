package ua.notification.service.entity.additional

import ua.notification.service.entity.Task
import ua.notification.service.entity.TaskMetadata

data class TaskTuple(
    val task: Task,
    val taskMetadata: TaskMetadata
)
