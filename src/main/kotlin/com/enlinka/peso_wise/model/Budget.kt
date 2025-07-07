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
    name = "budget",
    indexes = [
        Index(name = "idx_budget_category_id", columnList = "category_id")
    ]
)
data class Budget(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "category_id", length = 36, nullable = false)
    @field:NotBlank(message = "Category ID is required")
    val categoryId: String,

    @Column(name = "total_budget_amount", nullable = false)
    @field:NotNull(message = "Total budget amount is required")
    @field:PositiveOrZero(message = "Total budget amount must be positive or zero")
    var totalBudgetAmount: Double,

    @Column(name = "total_used_amount", nullable = false)
    @field:PositiveOrZero(message = "Total used amount must be positive or zero")
    var totalUsedAmount: Double = 0.0,

    @Column(name = "sub_categories", columnDefinition = "JSON")
    var subCategories: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Relationship to Category
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", insertable = false, updatable = false)
    var category: Category? = null

    // Default constructor for JPA
    constructor() : this(
        id = UUID.randomUUID().toString(),
        categoryId = "",
        totalBudgetAmount = 0.0,
        totalUsedAmount = 0.0
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Budget) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Budget(id='$id', categoryId='$categoryId', totalBudgetAmount=$totalBudgetAmount, totalUsedAmount=$totalUsedAmount)"
    }
}
