package ua.notification.service.entity

import jakarta.persistence.*
import ua.notification.service.entity.additional.ProcessStatus
import ua.notification.service.entity.additional.SendType
import java.util.*

@Entity
@Table(name = "direct_task")
data class DirectTask(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    val id: Long? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "creation_time")
    val creationTime: Date = Date(),

    @Enumerated(EnumType.STRING)
    @Column(name = "send_type", nullable = false)
    val sendType: SendType,

    @OneToOne(mappedBy = "directTask", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var metadata: DirectTaskMetadata? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "process_status", nullable = false)
    var status: ProcessStatus
)
