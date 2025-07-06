package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotNull
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "roles",
    indexes = [
        Index(name = "idx_roles_user_id", columnList = "user_id"),
        Index(name = "idx_roles_role", columnList = "role")
    ]
)
data class Role(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    @field:NotNull(message = "User is required")
    var user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    var role: RoleType = RoleType.USER,

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
        role = RoleType.USER
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Role) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "Role(id='$id', role=$role)"
    }
}

enum class RoleType {
    ADMIN, USER
}
