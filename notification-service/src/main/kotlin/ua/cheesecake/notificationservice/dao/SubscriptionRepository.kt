package ua.cheesecake.notificationservice.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.cheesecake.notificationservice.domain.Subscription

@Repository
interface SubscriptionRepository : JpaRepository<Subscription, Long> {
    fun findByUserId(userId: Long): Subscription
}