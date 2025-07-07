package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.PositiveOrZero
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "subscription_transaction",
    indexes = [
        Index(name = "idx_subscription_transaction_user_id", columnList = "user_id"),
        Index(name = "idx_subscription_transaction_status", columnList = "status"),
        Index(name = "idx_subscription_transaction_subscription", columnList = "subscription")
    ]
)
data class SubscriptionTransaction(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "user_id", length = 36, nullable = false)
    @field:NotBlank(message = "User ID is required")
    val userId: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "subscription", nullable = false)
    @field:NotNull(message = "Subscription type is required")
    var subscription: SubscriptionType,

    @Enumerated(EnumType.STRING)
    @Column(name = "tier", nullable = false)
    @field:NotNull(message = "Tier is required")
    var tier: TransactionTier,

    @Column(name = "amount", nullable = false)
    @field:NotNull(message = "Amount is required")
    @field:PositiveOrZero(message = "Amount must be positive or zero")
    var amount: Double,

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    @field:NotNull(message = "Status is required")
    var status: TransactionStatus = TransactionStatus.WAITING,

    @Column(name = "payment_method", length = 100, nullable = false)
    @field:NotBlank(message = "Payment method is required")
    var paymentMethod: String,

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
        subscription = SubscriptionType.FREE,
        tier = TransactionTier.UPGRADE,
        amount = 0.0,
        status = TransactionStatus.WAITING,
        paymentMethod = ""
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SubscriptionTransaction) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "SubscriptionTransaction(id='$id', userId='$userId', subscription=$subscription, tier=$tier, amount=$amount, status=$status)"
    }
}

enum class TransactionTier {
    UPGRADE, DOWNGRADE
}

enum class TransactionStatus {
    SUCCESSFUL, DENIED, WAITING
}
