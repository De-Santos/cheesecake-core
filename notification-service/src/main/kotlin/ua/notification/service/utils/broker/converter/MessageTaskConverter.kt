package ua.notification.service.utils.broker.converter

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.amqp.core.Message
import org.springframework.amqp.core.MessageProperties
import org.springframework.amqp.support.converter.AbstractMessageConverter
import org.springframework.stereotype.Component
import ua.notification.service.config.Property
import ua.notification.service.entity.additional.MessageTask

@Component(Property.messageTaskBeanName)
class MessageTaskConverter(
    private val objectMapper: ObjectMapper
) : AbstractMessageConverter() {

    override fun fromMessage(message: Message): MessageTask {
        val payload = message.body
        return objectMapper.readValue(payload, MessageTask::class.java)
    }

    override fun createMessage(obj: Any, messageProperties: MessageProperties): Message {
        val payload = objectMapper.writeValueAsBytes(obj)
        return Message(payload, messageProperties)
    }
}
