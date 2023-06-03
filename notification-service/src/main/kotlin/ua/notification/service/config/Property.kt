package ua.notification.service.config

object Property {
    //QUEUES
    const val taskQueueName: String = "task-queue"
    const val smsQueueName: String = "sms-queue"
    const val emailQueueName: String = "email-queue"

    //TAGS
    const val smsTag: String = "sms-tag"
    const val emailTag: String = "email-tag"

    //EXCHANGE
    const val directExchangeName: String = "notification-exchange"

    //BEAN NAME
    const val messageTaskBeanName: String = "messageTask"
}