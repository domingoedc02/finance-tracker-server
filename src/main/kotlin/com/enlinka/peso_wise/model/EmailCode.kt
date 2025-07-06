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
    name = "email_code",
    indexes = [
        Index(name = "idx_email_code_email", columnList = "email"),
        Index(name = "idx_email_code_device_id", columnList = "device_id"),
        Index(name = "idx_email_code_code", columnList = "code"),
        Index(name = "idx_email_code_is_used", columnList = "is_used"),
        Index(name = "idx_email_code_is_expired", columnList = "is_expired")
    ]
)
data class EmailCode(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @Column(name = "code", nullable = false, length = 10)
    @field:NotBlank(message = "Code is required")
    @field:Size(max = 10, message = "Code must be less than 10 characters")
    var code: String,

    @Column(name = "email", nullable = false)
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    @field:Size(max = 255, message = "Email must be less than 255 characters")
    var email: String,

    @Column(name = "device_id", nullable = false)
    @field:NotBlank(message = "Device ID is required")
    @field:Size(max = 255, message = "Device ID must be less than 255 characters")
    var deviceId: String,

    @Column(name = "is_used", nullable = false)
    var isUsed: Boolean = false,

    @Column(name = "is_expired", nullable = false)
    var isExpired: Boolean = false,

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
        code = "",
        email = "",
        deviceId = "",
        isUsed = false,
        isExpired = false
    )

    fun isValid(): Boolean {
        return !isUsed && !isExpired && createdAt.plusMinutes(15).isAfter(LocalDateTime.now())
    }

    fun markAsUsed() {
        isUsed = true
        updatedAt = LocalDateTime.now()
    }

    fun markAsExpired() {
        isExpired = true
        updatedAt = LocalDateTime.now()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is EmailCode) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "EmailCode(id='$id', email='$email', deviceId='$deviceId', isUsed=$isUsed, isExpired=$isExpired)"
    }
}
