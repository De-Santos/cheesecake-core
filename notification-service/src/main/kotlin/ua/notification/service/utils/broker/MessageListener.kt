package ua.notification.service.utils.broker

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ua.notification.service.config.Property
import ua.notification.service.entity.Task

@Component
class MessageListener {

    @RabbitListener(
        queues = [Property.taskQueueName],
        messageConverter = "taskMessageConverter"
    )
    fun receive(task: Task) {
        println("Received task: $task")
    }
}
