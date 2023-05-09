package ua.cheesecake.notificationservice.domain

import jakarta.persistence.*
import ua.cheesecake.notificationservice.facade.NotifyType

@Entity
class Subscription(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    var id: Long? = null,

    @Column(name = "user_id", nullable = false)
    var userId: Long,

    @ElementCollection(targetClass = NotifyType::class)
    var messengers: List<NotifyType> = emptyList()
)
