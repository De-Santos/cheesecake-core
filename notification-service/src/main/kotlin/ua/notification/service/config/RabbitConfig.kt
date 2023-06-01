package ua.notification.service.config

import org.springframework.amqp.core.Binding
import org.springframework.amqp.core.BindingBuilder
import org.springframework.amqp.core.DirectExchange
import org.springframework.amqp.core.Queue
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class RabbitConfig {
    @Bean
    fun directExchange(): DirectExchange {
        return DirectExchange(Property.directExchangeName)
    }

    @Bean
    fun taskQueue(): Queue {
        return Queue(Property.taskQueueName, true)
    }

    @Bean
    fun smsQueue(): Queue {
        return Queue(Property.smsQueueName, true)
    }

    @Bean
    fun emailQueue(): Queue {
        return Queue(Property.emailQueueName, true)
    }

    @Bean
    fun smsBinding(smsQueue: Queue, directExchange: DirectExchange): Binding {
        return BindingBuilder.bind(smsQueue).to(directExchange).with(Property.smsTag)
    }

    @Bean
    fun emailBinding(emailQueue: Queue, directExchange: DirectExchange): Binding {
        return BindingBuilder.bind(emailQueue).to(directExchange).with(Property.emailTag)
    }

    @Bean
    fun rabbitTemplate(connectionFactory: ConnectionFactory): RabbitTemplate {
        return RabbitTemplate(connectionFactory)
    }
}
