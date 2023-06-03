package ua.notification.service.utils.request.accelerator

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.entity.additional.notification.NotificationMethod
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.utils.broker.MessageBroker
import ua.notification.service.utils.builder.EntityBuilder
import ua.notification.service.utils.request.accelerator.mapper.NotificationPrincipalRowMapper
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet

@Component
class JdbcAccelerator(
    private val jdbc: JdbcTemplate,
    private val broker: MessageBroker,
    private val builder: EntityBuilder,
) {
    private val rowMapper: NotificationPrincipalRowMapper = NotificationPrincipalRowMapper()

    companion object {
        const val COUNT_OF_ROWS: String = """
            SELECT COUNT(id) FROM users
            """
        const val SELECT_BATCH: String = """
            SELECT users.id, users.name, user_private_data.email, user_private_data.phone_number
            FROM users
            JOIN user_private_data ON users.id = user_private_data.user_id
            WHERE users.id > ? AND users.id <= ?
        """
    }

    @Transactional
    fun createNotification(task: MessageTask) {
        val rowsCount: Long = jdbc.queryForObject(COUNT_OF_ROWS, Long::class.java)!!
        if (rowsCount == 0L) return
        this.batchCreateNotification(task, rowsCount)
    }

    private fun batchCreateNotification(task: MessageTask, rowsCount: Long) {
        val step: Long = 100
        var start: Long = 0
        var end: Long = step
        while (start <= rowsCount) {
            jdbc.query(this.selectBatchBuilder(start, end)) {
                this.createNotificationResultSetExtractor(it, task)
            }
            start = end
            end += step
        }
    }

    private fun selectBatchBuilder(start: Long, end: Long): PreparedStatementCreator {
        return PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_BATCH)
            ps.setLong(1, start)
            ps.setLong(2, end)
            ps
        }
    }

    private fun createNotificationResultSetExtractor(rs: ResultSet, task: MessageTask) {
        while (!rs.isAfterLast) {
            val principal: NotificationPrincipal = rowMapper.mapRow(rs, 0)
            broker.sendNotification(
                builder.buildNotification(principal, NotificationMethod.EMAIL, task)
            )
            rs.next()
        }
    }
}