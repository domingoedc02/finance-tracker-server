package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "categories",
    indexes = [
        Index(name = "idx_categories_user_id", columnList = "user_id"),
        Index(name = "idx_categories_name", columnList = "name")
    ]
)
data class Category(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    var user: User,

    @Column(name = "name", nullable = false)
    @field:NotBlank(message = "Category name is required")
    @field:Size(max = 255, message = "Category name must be less than 255 characters")
    var name: String,

    @Column(name = "icon")
    @field:Size(max = 255, message = "Icon must be less than 255 characters")
    var icon: String? = null,

    @Column(name = "color", length = 50)
    @field:Size(max = 50, message = "Color must be less than 50 characters")
    var color: String? = null,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Relationships
    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val subcategories: MutableSet<SubCategory> = mutableSetOf()

    @OneToMany(mappedBy = "category", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val budgets: MutableSet<Budget> = mutableSetOf()

    // Default constructor for JPA
    constructor() : this(
        id = UUID.randomUUID().toString(),
        user = User(),
        name = "",
        icon = null,
        color = null
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Category) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Category(id='$id', name='$name', icon='$icon', color='$color')"
    }
}
