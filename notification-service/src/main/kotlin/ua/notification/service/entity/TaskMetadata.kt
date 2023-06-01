package ua.notification.service.entity

import jakarta.persistence.*
import ua.notification.service.entity.additional.NotifyType

@Entity
@Table(name = "task_metadata")
data class TaskMetadata(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    val id: Long? = null,

    @Column(name = "message")
    val message: String,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    var task: Task? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "notification_type", nullable = false)
    val notifyType: NotifyType
) {
    override fun toString(): String {
        return "TaskMetadata(id=$id, message='$message', notificationType=$notifyType)"
    }
}