package ua.notification.service.utils.request.accelerator

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.SingleColumnRowMapper
import org.springframework.stereotype.Component
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.notification.NotificationMethod
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.utils.broker.MessageBroker
import ua.notification.service.utils.builder.EntityBuilder
import ua.notification.service.utils.request.accelerator.mapper.NotificationPrincipalRowMapper
import ua.notification.service.utils.request.accelerator.mapper.ProcessStatusRowMapper
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.util.*

@Component
class JdbcAccelerator(
    private val jdbc: JdbcTemplate,
    private val broker: MessageBroker,
    private val builder: EntityBuilder,
) {
    private val notificationPrincipalRowMapper: NotificationPrincipalRowMapper = NotificationPrincipalRowMapper()
    private val processStatusRowMapper: ProcessStatusRowMapper = ProcessStatusRowMapper()

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
        const val SET_TASK_STATUS: String = """
            UPDATE task  
            SET process_status=?
            WHERE id = ?
        """
        const val SELECT_INFO_BY_ID: String = """
            SELECT process_status
            FROM task
            WHERE id = ?
        """
        const val SELECT_ALL_IDS: String = """
            SELECT id
            FROM task
        """
        const val SELECT_ALL_ACTIVE_IDS: String = """
            SELECT id
            FROM task
            WHERE process_status = 'IN_PROCESS' OR process_status = 'PENDING'
        """
        const val SELECT_BY_STATUS: String = """
            SELECT id
            FROM task
            WHERE process_status = ?
        """
    }

    fun createNotification(task: MessageTask) {
        val rowsCount: Long = jdbc.queryForObject(COUNT_OF_ROWS, Long::class.java)!!
        if (rowsCount == 0L) return this.updateTaskStatus(task.id, ProcessStatus.DONE)
        this.updateTaskStatus(task.id, ProcessStatus.IN_PROCESS)
        this.batchCreateNotification(task, rowsCount)

    }

    fun getStatusById(id: Long): Optional<ProcessStatus> {
        val psc = PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_INFO_BY_ID)
            ps.setLong(1, id)
            ps
        }
        val resultList: List<ProcessStatus> = jdbc.query(psc, processStatusRowMapper)
        return resultList.firstOrNull()?.let { Optional.of(it) } ?: Optional.empty()
    }

    fun getAllIds(): List<Long> {
        return jdbc.queryForList(SELECT_ALL_IDS, Long::class.java)
    }

    fun getActiveIds(): List<Long> {
        return jdbc.queryForList(SELECT_ALL_ACTIVE_IDS, Long::class.java)
    }

    fun getIdsByStatus(status: ProcessStatus): List<Long> {
        val psc = PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_BY_STATUS)
            ps.setString(1, status.name)
            ps
        }
        return jdbc.query(psc, SingleColumnRowMapper(Long::class.java))
    }

    private fun updateTaskStatus(id: Long, status: ProcessStatus) {
        jdbc.update(SET_TASK_STATUS, status.name, id)
    }

    private fun batchCreateNotification(task: MessageTask, rowsCount: Long) {
        val batchSize: Long = 1000
        var start: Long = 0
        var end: Long = batchSize
        try {
            while (start < rowsCount) {
                jdbc.query(selectBatchBuilder(start, end)) {
                    this.createNotificationResultSetExtractor(it, task)
                }
                start += batchSize
                end = start + batchSize
            }
            this.updateTaskStatus(task.id, ProcessStatus.DONE)
        } catch (e: Exception) {
            this.updateTaskStatus(task.id, ProcessStatus.ERROR)
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
            val principal: NotificationPrincipal = notificationPrincipalRowMapper.mapRow(rs, 0)
            broker.sendNotification(
                builder.buildNotification(principal, NotificationMethod.EMAIL, task)
            )
            rs.next()
        }
    }
}