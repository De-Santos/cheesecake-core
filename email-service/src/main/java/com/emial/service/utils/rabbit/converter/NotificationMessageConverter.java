package com.emial.service.utils.rabbit.converter;

import com.emial.service.entity.Notification;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.support.converter.AbstractMessageConverter;
import org.springframework.amqp.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

@Component("notificationMessageConverter")
@RequiredArgsConstructor
public class NotificationMessageConverter extends AbstractMessageConverter {
    private final ObjectMapper objectMapper;

    @SneakyThrows
    @Override
    protected Message createMessage(Object object, MessageProperties messageProperties) {
        byte[] payload = objectMapper.writeValueAsBytes(object);
        return new Message(payload, messageProperties);
    }

    @SneakyThrows
    @Override
    public Notification fromMessage(Message message) throws MessageConversionException {
        return objectMapper.readValue(message.getBody(), Notification.class);
    }
}
