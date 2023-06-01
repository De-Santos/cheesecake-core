package ua.notification.service.utils.request.accelerator

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component
import ua.notification.service.entity.Task
import ua.notification.service.utils.request.accelerator.mapper.NotificationPrincipalRowMapper

@Component
class JdbcAccelerator(
    private val jdbc: JdbcTemplate,
    private val rowMapper: NotificationPrincipalRowMapper
) {
    companion object {
        const val FIND_ALL_USERS: String = """
            SELECT users.id, users.name, user_private_data.email, user_private_data.phone_number
            FROM users, user_private_data
            """
    }

    fun createNotification(task: Task) {
        rowMapper.setTask(task)
        jdbc.query(FIND_ALL_USERS, rowMapper)
    }
}