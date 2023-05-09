package ua.cheesecake.notificationservice.controller;

import io.klogging.Klogging
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import ua.cheesecake.notificationservice.domain.Subscription
import ua.cheesecake.notificationservice.dto.SubscriptionDto
import ua.cheesecake.notificationservice.service.SubscriptionService

@RestController
class RestSubscriptionController(
    private val subscriptionService: SubscriptionService
) {

    @GetMapping
    suspend fun getSubscription(userId: Long): SubscriptionDto {
        logger.info("Getting subscription for user: $userId")
        val findByUserId = subscriptionService.findByUserId(userId)
        logger.info("Subscription found: $findByUserId")
        return findByUserId
    }

    @PostMapping
    suspend fun saveSubscription(subscription: SubscriptionDto): Subscription {
        logger.info("Saving subscription: $subscription")
        val saveSubscription = subscriptionService.saveSubscription(subscription)
        logger.info("Subscription saved: $saveSubscription")
        return saveSubscription
    }

    companion object : Klogging
}
