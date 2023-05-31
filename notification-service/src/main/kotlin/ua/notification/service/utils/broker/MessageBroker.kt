package ua.notification.service.utils.broker

import org.slf4j.LoggerFactory
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.stereotype.Component
import ua.notification.service.entity.additional.Task
import ua.notification.service.utils.builder.EntityBuilder

@Component
class MessageBroker(
    private val rabbit: RabbitTemplate,
    private val queue: Queue,
    private val entityBuilder: EntityBuilder,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun sendTask(task: Task) {
        log.info("Saving task in rabbitmq")
        return rabbit.send(queue.name, entityBuilder.buildMessage(task))
    }
}  