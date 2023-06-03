package ua.notification.service.utils.broker

import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.stereotype.Component
import ua.notification.service.config.Property
import ua.notification.service.entity.additional.MessageTask
import ua.notification.service.utils.request.accelerator.JdbcAccelerator

@Component
class MessageListener(
    private val accelerator: JdbcAccelerator
) {

    @RabbitListener(
        queues = [Property.taskQueueName],
        messageConverter = Property.messageTaskBeanName
    )
    fun receive(task: MessageTask) {
        println("receive task")
        accelerator.createNotification(task)
    }
}
