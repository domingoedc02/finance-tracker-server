package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "transaction",
    indexes = [
        Index(name = "idx_transaction_user_id", columnList = "user_id"),
        Index(name = "idx_transaction_group_id", columnList = "group_id"),
        Index(name = "idx_transaction_category", columnList = "category"),
        Index(name = "idx_transaction_status", columnList = "status"),
        Index(name = "idx_transaction_created_at", columnList = "created_at")
    ]
)
data class Transaction(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @field:NotNull(message = "User is required")
    var user: User,

    @Column(name = "group_id", length = 36)
    var groupId: String? = null,

    @Column(name = "category", nullable = false)
    @field:NotBlank(message = "Category is required")
    @field:Size(max = 255, message = "Category must be less than 255 characters")
    var category: String,

    @Column(name = "subcategory")
    @field:Size(max = 255, message = "Subcategory must be less than 255 characters")
    var subcategory: String? = null,

    @Column(name = "status", nullable = false)
    @field:NotBlank(message = "Status is required")
    @field:Size(max = 50, message = "Status must be less than 50 characters")
    var status: String,

    @Column(name = "amount", nullable = false)
    @field:NotNull(message = "Amount is required")
    var amount: Double,

    @Column(name = "note", columnDefinition = "TEXT")
    var note: String? = null,

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
        groupId = null,
        category = "",
        subcategory = null,
        status = "",
        amount = 0.0,
        note = null
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Transaction) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Transaction(id='$id', category='$category', subcategory='$subcategory', amount=$amount, status='$status')"
    }
}
