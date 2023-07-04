package ua.notification.service.service

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import ua.notification.service.entity.DirectTask
import ua.notification.service.entity.Task
import ua.notification.service.entity.TaskMetadata
import ua.notification.service.entity.additional.*
import ua.notification.service.entity.additional.notification.NotificationMethod
import ua.notification.service.entity.additional.notification.NotificationPrincipal
import ua.notification.service.utils.broker.MessageBroker
import ua.notification.service.utils.builder.EntityBuilder

@Service
class MessageService(
    private val broker: MessageBroker,
    private val entityBuilder: EntityBuilder,
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun sendTask(tuple: Tuple<Task, TaskMetadata>) {
        log.info("Saving task in rabbitmq")
        broker.sendTask(entityBuilder.buildMessageTask(tuple))
    }

    fun sendMessageTask(tuple: Tuple<MessageTask, NotificationPrincipal>) {
        if (tuple.arg2.emailNotification) this.sendEmail(tuple.arg2, tuple.arg1)
        if (tuple.arg2.smsNotification) this.sendSMS(tuple.arg2, tuple.arg1)
    }

    fun sendDirectTask(tuple: Tuple<DirectTask, NotificationPrincipal>, sendType: SendType) {
        if (sendType == SendType.FORCE || tuple.arg2.emailNotification) this.sendEmail(tuple.arg2, tuple.arg1)
        if (sendType == SendType.FORCE || tuple.arg2.smsNotification) this.sendSMS(tuple.arg2, tuple.arg1)
    }

    private fun sendEmail(principal: NotificationPrincipal, directTask: DirectTask) {
        if (directTask.metadata!!.notifyType == NotifyType.SMS) return
        broker.sendNotification(
            entityBuilder.buildNotification(
                principal,
                NotificationMethod.EMAIL,
                directTask
            )
        )
    }

    private fun sendSMS(principal: NotificationPrincipal, directTask: DirectTask) {
        if (directTask.metadata!!.notifyType == NotifyType.EMAIL) return
        broker.sendNotification(
            entityBuilder.buildNotification(
                principal,
                NotificationMethod.SMS,
                directTask
            )
        )
    }

    private fun sendEmail(principal: NotificationPrincipal, task: MessageTask) {
        if (task.notifyType == NotifyType.SMS) return
        broker.sendNotification(
            entityBuilder.buildNotification(
                principal,
                NotificationMethod.EMAIL,
                task
            )
        )
    }

    private fun sendSMS(principal: NotificationPrincipal, task: MessageTask) {
        if (task.notifyType == NotifyType.EMAIL) return
        broker.sendNotification(
            entityBuilder.buildNotification(
                principal,
                NotificationMethod.SMS,
                task
            )
        )
    }
}
