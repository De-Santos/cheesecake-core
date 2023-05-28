package ua.notification.service.service

import org.springframework.stereotype.Service
import ua.notification.service.dao.SubscriptionRepository
import ua.notification.service.dto.SubscriptionDto
import ua.notification.service.utils.mapper.toDto
import ua.notification.service.utils.mapper.toSubscription

@Service
class SubscriptionService(
    private val subscriptionRepository: SubscriptionRepository

) {
    fun findByUserId(userId: Long): SubscriptionDto = subscriptionRepository.findByUserId(userId).toDto()
    fun saveSubscription(dto: SubscriptionDto) = subscriptionRepository.save(dto.toSubscription())
}
