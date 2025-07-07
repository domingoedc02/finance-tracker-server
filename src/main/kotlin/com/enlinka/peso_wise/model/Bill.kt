package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PositiveOrZero
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "bills",
    indexes = [
        Index(name = "idx_bills_user_id", columnList = "user_id"),
        Index(name = "idx_bills_category", columnList = "category"),
        Index(name = "idx_bills_recurring", columnList = "recurring"),
        Index(name = "idx_bills_transaction_date", columnList = "transaction_date")
    ]
)
data class Bill(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "user_id", length = 36, nullable = false)
    @field:NotBlank(message = "User ID is required")
    val userId: String,

    @Column(name = "name", length = 255, nullable = false)
    @field:NotBlank(message = "Name is required")
    var name: String,

    @Column(name = "category", length = 255, nullable = false)
    @field:NotBlank(message = "Category is required")
    var category: String,

    @Column(name = "subcategory", length = 255)
    var subcategory: String? = null,

    @Column(name = "amount", length = 255, nullable = false)
    @field:NotBlank(message = "Amount is required")
    var amount: String,

    @Column(name = "payment_terms")
    var paymentTerms: Int? = null,

    @Column(name = "recurring", nullable = false)
    var recurring: Boolean = false,

    @Column(name = "recurring_frequency", length = 50)
    var recurringFrequency: String? = null,

    @Column(name = "installment_amount")
    @field:PositiveOrZero(message = "Installment amount must be positive or zero")
    var installmentAmount: Double? = null,

    @Column(name = "installment_plan", length = 255)
    var installmentPlan: String? = null,

    @Column(name = "installment_interest")
    @field:PositiveOrZero(message = "Installment interest must be positive or zero")
    var installmentInterest: Double? = null,

    @Column(name = "transaction_date")
    var transactionDate: LocalDate? = null,

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
        name = "",
        category = "",
        subcategory = null,
        amount = ""
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Bill) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Bill(id='$id', userId='$userId', name='$name', category='$category', amount='$amount', recurring=$recurring)"
    }
}
