package ua.notification.service.domain

import jakarta.persistence.*
import ua.notification.service.facade.NotifyType


@Entity
@Table(name = "messages")
data class Message(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "send_status", nullable = false)
    var sendStatus: SendStatus = SendStatus.NEW,


    @Enumerated(EnumType.ORDINAL)
    @Column(name = "notify_type", nullable = false)
    var notifyType: NotifyType
)

