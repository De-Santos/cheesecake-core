package ua.cheesecake.notificationservice.service

import org.springframework.stereotype.Service
import ua.cheesecake.notificationservice.dao.SubscriptionRepository
import ua.cheesecake.notificationservice.dto.SubscriptionDto
import ua.cheesecake.notificationservice.utils.mapper.toDto
import ua.cheesecake.notificationservice.utils.mapper.toSubscription

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository

) {
    fun findByUserId(userId: Long): SubscriptionDto = subscriptionRepository.findByUserId(userId).toDto()
    fun saveSubscription(dto: SubscriptionDto) = subscriptionRepository.save(dto.toSubscription())
}
