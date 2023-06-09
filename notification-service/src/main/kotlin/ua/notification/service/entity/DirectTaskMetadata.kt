package ua.notification.service.entity

import jakarta.persistence.*
import ua.notification.service.entity.additional.NotifyType

@Entity
@Table(name = "direct_task_metadata")
data class DirectTaskMetadata(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    val id: Long? = null,

    @Column(name = "message")
    val message: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "direct_task_id", referencedColumnName = "id")
    var directTask: DirectTask? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    val notifyType: NotifyType,

    @Column(name = "user_id")
    val userId: Long
)
