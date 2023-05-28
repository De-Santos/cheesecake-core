package ua.notification.service.dao

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import ua.notification.service.domain.Subscription


@Repository
interface SubscriptionRepository : JpaRepository<Subscription, Long> {
    fun findByUserId(userId: Long): Subscription
}