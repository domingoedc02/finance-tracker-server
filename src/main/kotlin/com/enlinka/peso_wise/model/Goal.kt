package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "goal",
    indexes = [
        Index(name = "idx_goal_user_id", columnList = "user_id"),
        Index(name = "idx_goal_start_date", columnList = "start_date"),
        Index(name = "idx_goal_end_date", columnList = "end_date")
    ]
)
data class Goal(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @field:NotNull(message = "User is required")
    var user: User,

    @Column(name = "name", nullable = false)
    @field:NotBlank(message = "Goal name is required")
    @field:Size(max = 255, message = "Goal name must be less than 255 characters")
    var name: String,

    @Column(name = "color", length = 50)
    @field:Size(max = 50, message = "Color must be less than 50 characters")
    var color: String? = null,

    @Column(name = "note", columnDefinition = "TEXT")
    var note: String? = null,

    @Column(name = "icon")
    @field:Size(max = 255, message = "Icon must be less than 255 characters")
    var icon: String? = null,

    @Column(name = "target_amount", nullable = false)
    @field:NotNull(message = "Target amount is required")
    var targetAmount: Double,

    @Column(name = "current_amount", nullable = false)
    var currentAmount: Double = 0.0,

    @Column(name = "start_date", nullable = false)
    @field:NotNull(message = "Start date is required")
    var startDate: LocalDate,

    @Column(name = "end_date")
    var endDate: LocalDate? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Default constructor for JPA
    constructor() : this(
        id = UUID.randomUUID().toString(),
        user = User(),
        name = "",
        color = null,
        note = null,
        icon = null,
        targetAmount = 0.0,
        currentAmount = 0.0,
        startDate = LocalDate.now(),
        endDate = null
    )

    fun getProgressPercentage(): Double {
        return if (targetAmount > 0) {
            (currentAmount / targetAmount) * 100
        } else 0.0
    }

    fun getRemainingAmount(): Double {
        return maxOf(0.0, targetAmount - currentAmount)
    }

    fun isCompleted(): Boolean {
        return currentAmount >= targetAmount
    }

    fun addContribution(amount: Double) {
        currentAmount += amount
        updatedAt = LocalDateTime.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Goal) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Goal(id='$id', name='$name', targetAmount=$targetAmount, currentAmount=$currentAmount, progress=${getProgressPercentage()}%)"
    }
}
