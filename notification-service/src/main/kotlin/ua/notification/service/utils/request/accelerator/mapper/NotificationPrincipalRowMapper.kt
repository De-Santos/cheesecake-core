package ua.notification.service.utils.request.accelerator.mapper

import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Component
import ua.notification.service.entity.Task
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.utils.broker.MessageBroker
import java.sql.ResultSet

@Component
class NotificationPrincipalRowMapper(
    private val messageBroker: MessageBroker,
) : RowMapper<NotificationPrincipal> {
    private var task: Task? = null

    override fun mapRow(rs: ResultSet, rowNum: Int): NotificationPrincipal {
        TODO("implement me please")
    }

    fun setTask(task: Task) {
        this.task = task
    }
}