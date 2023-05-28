package ua.notification.service.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import ua.notification.service.domain.Message

@Repository
interface MessageRepository : JpaRepository<Message, Long> {

    @Query("select m from Message m where m.sendStatus = 0")
    fun findAllUnsentMessages(): List<Message>
}
