package ua.notification.service.utils.request.accelerator

import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.PreparedStatementCreator
import org.springframework.jdbc.core.SingleColumnRowMapper
import org.springframework.stereotype.Component
import ua.notification.service.dto.DirectNotificationResponse
import ua.notification.service.dto.NotificationResponse
import ua.notification.service.entity.ProcessMetadata
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.Tuple
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.service.MessageService
import ua.notification.service.utils.request.accelerator.mapper.*
import java.sql.Connection
import java.sql.PreparedStatement
import java.util.*

@Component
class JdbcAccelerator(
    private val jdbc: JdbcTemplate,
    private val messageService: MessageService,
) {
    private val directNotificationResponseRowMapper: DirectNotificationResponseRowMapper = DirectNotificationResponseRowMapper()
    private val notificationPrincipalRowMapper: NotificationPrincipalRowMapper = NotificationPrincipalRowMapper()
    private val notificationResponseRowMapper: NotificationResponseRowMapper = NotificationResponseRowMapper()
    private val processMetadataRowMapper: ProcessMetadataRowMapper = ProcessMetadataRowMapper()
    private val processStatusRowMapper: ProcessStatusRowMapper = ProcessStatusRowMapper()

    companion object {
        private const val BATCH_SIZE: Long = 1000
        const val SELECT_USERS_BATCH: String = """
                SELECT users.id, users.name, upd.email, upd.phone_number, uns.email_notification, uns.sms_notification
                FROM users
                JOIN user_private_data AS upd ON users.id = upd.user_id
                JOIN user_notification_settings AS uns ON users.id = uns.user_id
                LIMIT ?
                OFFSET ?
        """
        private const val SELECT_PRINCIPAL_BY_USER_ID: String = """
            SELECT users.id, users.name, upd.email, upd.phone_number, uns.email_notification, uns.sms_notification
            FROM users
            JOIN user_private_data AS upd ON users.id = upd.user_id
            JOIN user_notification_settings AS uns ON users.id = uns.user_id
            WHERE users.id = ?
        """
        private const val SET_TASK_STATUS: String = """
            UPDATE task  
            SET process_status=?
            WHERE id = ?
        """
        private const val SELECT_NOTIFICATION_BY_ID: String = """
            SELECT task.id AS task_id, task.create_time, task.process_status, process_metadata.id AS process_metadata_id
            FROM task
            JOIN process_metadata ON task.id = process_metadata.task_id
            WHERE task.id = ?
        """
        private const val SELECT_DIRECT_NOTIFICATION_BY_ID: String = """
            SELECT direct_task.id, direct_task.process_status, direct_task.creation_time, direct_task_metadata.user_id
            FROM direct_task
            JOIN direct_task_metadata ON direct_task.id = direct_task_metadata.direct_task_id
            WHERE direct_task.id = ?
        """
        private const val SELECT_STATUS_BY_ID: String = """
            SELECT process_status
            FROM task
            WHERE id = ?
        """
        private const val SELECT_ALL_TASK_IDS: String = """
            SELECT id
            FROM task
        """
        private const val SELECT_ALL_DIRECT_TASK_IDS: String = """
            SELECT id
            FROM direct_task
        """
        private const val SELECT_ALL_ACTIVE_IDS: String = """
            SELECT id
            FROM task
            WHERE process_status = 'IN_PROCESS' OR process_status = 'PENDING'
        """
        private const val SELECT_BY_STATUS: String = """
            SELECT id
            FROM task
            WHERE process_status = ?
        """
        private const val SELECT_PROCESS_METADATA_BY_ID: String = """
            SELECT *
            FROM process_metadata
            WHERE id = ?
        """
        private const val SET_END_TIME_IN_PROCESS_METADATA: String = """
            UPDATE process_metadata
            SET end_time = ?
            WHERE task_id = ?
        """
        private const val SET_START_TIME_IN_PROCESS_METADATA: String = """
            UPDATE process_metadata
            SET start_time = ?
            WHERE task_id = ?
        """
        private const val SET_USERS_PROCESSED: String = """
            UPDATE process_metadata
            SET users_processed = ?
            WHERE task_id = ?
        """
        private const val EXIST_USER_BY_ID: String = """
            SELECT EXISTS(SELECT 1 FROM users WHERE id = ?)
        """
    }

    fun createNotification(task: MessageTask) {
        this.updateTaskStatus(task.id, ProcessStatus.IN_PROCESS)
        this.batchCreateNotification(task)

    }

    fun getStatusById(id: Long): Optional<ProcessStatus> {
        val psc = PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_STATUS_BY_ID)
            ps.setLong(1, id)
            ps
        }
        val resultList: List<ProcessStatus> = jdbc.query(psc, processStatusRowMapper)
        return resultList.firstOrNull()?.let { Optional.of(it) } ?: Optional.empty()
    }

    fun getNotificationById(id: Long): Optional<NotificationResponse> {
        val psc = PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_NOTIFICATION_BY_ID)
            ps.setLong(1, id)
            ps
        }
        val resultList: List<NotificationResponse> = jdbc.query(psc, notificationResponseRowMapper)
        return resultList.firstOrNull()?.let { Optional.of(it) } ?: Optional.empty()
    }

    fun getDirectNotificationById(id: Long): Optional<DirectNotificationResponse> {
        val psc = PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_DIRECT_NOTIFICATION_BY_ID)
            ps.setLong(1, id)
            ps
        }
        val resultList: List<DirectNotificationResponse> = jdbc.query(psc, directNotificationResponseRowMapper)
        return resultList.firstOrNull()?.let { Optional.of(it) } ?: Optional.empty()
    }

    fun getProcessMetadataById(id: Long): Optional<ProcessMetadata> {
        val psc = PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_PROCESS_METADATA_BY_ID)
            ps.setLong(1, id)
            ps
        }
        val resultList: List<ProcessMetadata> = jdbc.query(psc, processMetadataRowMapper)
        return resultList.firstOrNull()?.let { Optional.of(it) } ?: Optional.empty()
    }

    fun getAllTaskIds(): List<Long> {
        return jdbc.queryForList(SELECT_ALL_TASK_IDS, Long::class.java)
    }

    fun getAllDirectTaskIds(): List<Long> {
        return jdbc.queryForList(SELECT_ALL_DIRECT_TASK_IDS, Long::class.java)
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

    fun existUserById(id: Long): Boolean {
        return jdbc.queryForObject(EXIST_USER_BY_ID, Boolean::class.java, id)
    }

    fun getNotificationPrincipalByUserId(id: Long): Optional<NotificationPrincipal> {
        val psc = PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_PRINCIPAL_BY_USER_ID)
            ps.setLong(1, id)
            ps
        }

        val resultList: List<NotificationPrincipal> = jdbc.query(psc, notificationPrincipalRowMapper)
        return resultList.firstOrNull()?.let { Optional.of(it) } ?: Optional.empty()
    }

    private fun batchCreateNotification(task: MessageTask) {
        var offset: Long = 0
        var userPrincipals: List<NotificationPrincipal>
        try {
            this.setStartTime(task.id)
            do {
                userPrincipals = this.fetchPrincipals(offset)
                this.processUsers(userPrincipals, task)
                this.setUsersProcessed(task.id, offset)
                offset += BATCH_SIZE
            } while (userPrincipals.isNotEmpty())
            this.setEndTime(task.id)
            this.updateTaskStatus(task.id, ProcessStatus.DONE)
        } catch (e: Exception) {
            this.updateTaskStatus(task.id, ProcessStatus.ERROR)
        }
    }

    private fun fetchPrincipals(offset: Long): List<NotificationPrincipal> {
        return jdbc.query(selectBatchBuilder(offset), notificationPrincipalRowMapper)
    }

    private fun setUsersProcessed(id: Long, quantity: Long) {
        jdbc.update(SET_USERS_PROCESSED, quantity, id)
    }

    private fun setStartTime(id: Long) {
        jdbc.update(SET_START_TIME_IN_PROCESS_METADATA, Date(), id)
    }

    private fun setEndTime(id: Long) {
        jdbc.update(SET_END_TIME_IN_PROCESS_METADATA, Date(), id)
    }

    private fun updateTaskStatus(id: Long, status: ProcessStatus) {
        jdbc.update(SET_TASK_STATUS, status.name, id)
    }

    private fun selectBatchBuilder(offset: Long): PreparedStatementCreator {
        return PreparedStatementCreator { con: Connection ->
            val ps: PreparedStatement = con.prepareStatement(SELECT_USERS_BATCH)
            ps.setLong(1, BATCH_SIZE)
            ps.setLong(2, offset)
            ps
        }
    }

    private fun processUsers(principals: List<NotificationPrincipal>, task: MessageTask) {
        principals.forEach { messageService.sendMessageTask(Tuple(task, it)) }
    }
}