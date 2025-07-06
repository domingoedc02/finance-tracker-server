package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "users",
    indexes = [
        Index(name = "idx_users_email", columnList = "email"),
        Index(name = "idx_users_device_id", columnList = "device_id")
    ]
)
data class User(
    @Id
    @Column(name = "user_id", length = 36)
    val userId: String = UUID.randomUUID().toString(),

    @Column(name = "name", nullable = false)
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 255, message = "Name must be less than 255 characters")
    var name: String,

    @Column(name = "email", nullable = false, unique = true)
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    @field:Size(max = 255, message = "Email must be less than 255 characters")
    var email: String,

    @Column(name = "device_id")
    @field:Size(max = 255, message = "Device ID must be less than 255 characters")
    var deviceId: String? = null,

    @Column(name = "password", nullable = false)
    @field:NotBlank(message = "Password is required")
    @field:Size(max = 255, message = "Password must be less than 255 characters")
    var password: String,

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    val createdAt: LocalDateTime = LocalDateTime.now(),

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    var updatedAt: LocalDateTime = LocalDateTime.now()
) {
    // Relationships
    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val subscriptions: MutableSet<Subscription> = mutableSetOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val subscriptionTransactions: MutableSet<SubscriptionTransaction> = mutableSetOf()

    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var role: Role? = null

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val categories: MutableSet<Category> = mutableSetOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val transactions: MutableSet<Transaction> = mutableSetOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val bills: MutableSet<Bill> = mutableSetOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val goals: MutableSet<Goal> = mutableSetOf()

    @OneToMany(mappedBy = "user", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    val userAccounts: MutableSet<UserAccount> = mutableSetOf()

    // Default constructor for JPA
    constructor() : this(
        userId = UUID.randomUUID().toString(),
        name = "",
        email = "",
        deviceId = null,
        password = ""
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is User) return false
        return userId == other.userId
    }

    override fun hashCode(): Int {
        return userId.hashCode()
    }

    override fun toString(): String {
        return "User(userId='$userId', name='$name', email='$email', deviceId='$deviceId')"
    }
}
