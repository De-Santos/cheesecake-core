package ua.notification.service.entity.additional

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "task")
data class Task(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", columnDefinition = "bigint")
    val id: Long? = null,

    val time: Date = Date(),

    @OneToOne(mappedBy = "task", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    var metadata: TaskMetadata? = null,

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "process_status", nullable = false)
    var status: ProcessStatus
) {
    override fun toString(): String {
        return "Task(id=$id, time=$time, status=$status)"
    }
}