package ua.cheesecake.notificationservice.domain

import jakarta.persistence.*
import org.hibernate.Hibernate
import ua.cheesecake.notificationservice.facade.NotificationData
import ua.cheesecake.notificationservice.facade.NotifyType

@Entity
data class Message(
    @Column(name = "message", nullable = false)
    override var message: String,
    @Column(name = "account", nullable = false)
    override var account: String,
    @Column(name = "notify_type", nullable = false)
    var notifyType: NotifyType
) : NotificationData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long? = null

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "send_status", nullable = false)
    var sendStatus = SendStatus.NEW

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || Hibernate.getClass(this) != Hibernate.getClass(other)) return false
        other as Message

        return (id != null) && (id == other.id)
    }

    override fun hashCode(): Int = javaClass.hashCode()

    @Override
    override fun toString(): String {
        return this::class.simpleName + "(id = $id , text = $message , account = $account , notifyType = $notifyType )"
    }
}

