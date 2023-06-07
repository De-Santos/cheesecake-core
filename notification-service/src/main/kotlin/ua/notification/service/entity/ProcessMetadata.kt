package ua.notification.service.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "process_metadata")
data class ProcessMetadata(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    val id: Long? = null,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id", referencedColumnName = "id")
    var task: Task?,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "start_time")
    val startTime: Date? = null,

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "end_time")
    val endTime: Date? = null,

    @Column(name = "users_processed")
    val usersProcessed: Long? = null,
)
