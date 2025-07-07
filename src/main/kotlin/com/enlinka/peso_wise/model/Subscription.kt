package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "subscription",
    indexes = [
        Index(name = "idx_subscription_user_id", columnList = "user_id"),
        Index(name = "idx_subscription_status", columnList = "status"),
        Index(name = "idx_subscription_type", columnList = "subscription")
    ]
)
data class Subscription(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "user_id", length = 36, nullable = false)
    @field:NotBlank(message = "User ID is required")
    val userId: String,

    @Column(name = "status", length = 50, nullable = false)
    @field:NotBlank(message = "Status is required")
    var status: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription", nullable = false)
    @field:NotNull(message = "Subscription type is required")
    var subscriptionType: SubscriptionType = SubscriptionType.FREE,

    @Column(name = "start_date", nullable = false)
    @field:NotNull(message = "Start date is required")
    var startDate: LocalDateTime,

    @Column(name = "expire_date")
    var expireDate: LocalDateTime? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Relationship to User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    var user: User? = null

    // Default constructor for JPA
    constructor() : this(
        id = UUID.randomUUID().toString(),
        userId = "",
        status = "",
        subscriptionType = SubscriptionType.FREE,
        startDate = LocalDateTime.now()
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Subscription) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Subscription(id='$id', userId='$userId', status='$status', subscriptionType=$subscriptionType)"
    }
}

enum class SubscriptionType {
    PRO, FREE, TRIAL
}
