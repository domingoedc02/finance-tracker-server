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
    name = "subcategory",
    indexes = [
        Index(name = "idx_subcategory_category_id", columnList = "category_id"),
        Index(name = "idx_subcategory_name", columnList = "name")
    ]
)
data class SubCategory(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "category_id", length = 36, nullable = false)
    @field:NotBlank(message = "Category ID is required")
    val categoryId: String,

    @Column(name = "name", length = 255, nullable = false)
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 255, message = "Name must be less than 255 characters")
    var name: String,

    @Column(name = "icon", length = 255)
    var icon: String? = null,

    @Column(name = "color", length = 50)
    var color: String? = null,

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
        name = ""
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is SubCategory) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "SubCategory(id='$id', categoryId='$categoryId', name='$name', color='$color')"
    }
}
