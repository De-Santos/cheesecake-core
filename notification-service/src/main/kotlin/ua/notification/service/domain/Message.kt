package ua.notification.service.domain

import jakarta.persistence.*


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
)

