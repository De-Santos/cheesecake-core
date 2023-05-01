package ua.cheesecake.notificationservice.dao

import org.springframework.stereotype.Repository
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import ua.cheesecake.notificationservice.domain.Message

@Repository
interface MessageRepository : JpaRepository<Message, Long> {
    @Query("select m from Message m where m.sendStatus = 0")
    fun findAllNotSent(): List<Message>
}
