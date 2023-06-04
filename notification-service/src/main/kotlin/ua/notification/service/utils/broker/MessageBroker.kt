package ua.notification.service.utils.broker

import org.slf4j.LoggerFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import ua.notification.service.config.RabbitConfig
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.entity.additional.notification.Notification
import ua.notification.service.utils.broker.builder.MessageBuilder

@Component
class MessageBroker(
    private val rabbit: RabbitTemplate,
    private val rabbitConfig: RabbitConfig,
    private val messageBuilder: MessageBuilder,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun sendTask(task: MessageTask) {
        log.info("Saving task in rabbitmq")
        rabbit.send(rabbitConfig.taskQueue().name, messageBuilder.build(task))
    }

    fun sendNotification(notification: Notification): Notification {
        rabbit.send(rabbitConfig.directExchange().name, notification.method.toTag(), messageBuilder.build(notification))
        return notification
    }
}  