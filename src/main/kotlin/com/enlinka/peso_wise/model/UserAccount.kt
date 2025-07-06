package com.enlinka.peso_wise.model

import jakarta.persistence.*
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(
    name = "user_accounts",
    indexes = [
        Index(name = "idx_user_accounts_user_id", columnList = "user_id"),
        Index(name = "idx_user_accounts_type", columnList = "type"),
        Index(name = "idx_user_accounts_card_network", columnList = "card_network")
    ]
)
data class UserAccount(
    @Id
    @Column(name = "id", length = 36)
    val id: String = UUID.randomUUID().toString(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @field:NotNull(message = "User is required")
    var user: User,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    var type: AccountType,

    @Column(name = "last_four_digits")
    var lastFourDigits: Int? = null,

    @Column(name = "balance", nullable = false)
    var balance: Double = 0.0,

    @Column(name = "card_network")
    @field:Size(max = 50, message = "Card network must be less than 50 characters")
    var cardNetwork: String? = null,

    @Column(name = "expiration_date")
    @field:Size(max = 20, message = "Expiration date must be less than 20 characters")
    var expirationDate: String? = null,

    @Column(name = "credit_limit")
    var creditLimit: Double? = null,

    @Column(name = "credit_cut_off")
    var creditCutOff: Int? = null,

    @Column(name = "credit_due_days_after")
    var creditDueDaysAfter: Int? = null,

    @Column(name = "credit_fixed_due_date")
    var creditFixedDueDate: LocalDate? = null,

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
        type = AccountType.BANK_ACCOUNT,
        lastFourDigits = null,
        balance = 0.0,
        cardNetwork = null,
        expirationDate = null,
        creditLimit = null,
        creditCutOff = null,
        creditDueDaysAfter = null,
        creditFixedDueDate = null
    )

    fun getAvailableCredit(): Double? {
        return if (type == AccountType.CREDIT_CARD && creditLimit != null) {
            creditLimit!! - balance
        } else null
    }

    fun isCreditCard(): Boolean {
        return type == AccountType.CREDIT_CARD
    }

    fun isDebitCard(): Boolean {
        return type == AccountType.DEBIT
    }

    fun isBankAccount(): Boolean {
        return type == AccountType.BANK_ACCOUNT
    }

    fun getMaskedCardNumber(): String? {
        return if (lastFourDigits != null) {
            "**** **** **** $lastFourDigits"
        } else null
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is UserAccount) return false
        return id == other.id
    }

    override fun hashCode(): Int {
        return id.hashCode()
    }

    override fun toString(): String {
        return "UserAccount(id='$id', type=$type, balance=$balance, lastFourDigits=$lastFourDigits)"
    }
}

enum class AccountType {
    CREDIT_CARD, DEBIT, BANK_ACCOUNT
}
