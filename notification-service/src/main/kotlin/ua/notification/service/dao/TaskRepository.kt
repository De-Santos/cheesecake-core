package ua.notification.service.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.notification.service.entity.Task

@Repository
interface TaskRepository : JpaRepository<Task, Long>