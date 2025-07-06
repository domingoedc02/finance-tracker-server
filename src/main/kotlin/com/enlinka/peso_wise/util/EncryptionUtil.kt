package com.enlinka.peso_wise.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.crypto.encrypt.TextEncryptor
import org.springframework.stereotype.Component

@Component
class EncryptionUtil {

    @Autowired
    private lateinit var textEncryptor: TextEncryptor

    @Autowired
    private lateinit var queryableTextEncryptor: TextEncryptor

    /**
     * Encrypt sensitive text data for storage
     */
    fun encrypt(plainText: String?): String? {
        return plainText?.let { textEncryptor.encrypt(it) }
    }

    /**
     * Decrypt encrypted data for retrieval
     */
    fun decrypt(encryptedText: String?): String? {
        return encryptedText?.let { textEncryptor.decrypt(it) }
    }

    /**
     * Encrypt data that needs to be queryable (for search operations)
     */
    fun encryptQueryable(plainText: String?): String? {
        return plainText?.let { queryableTextEncryptor.encrypt(it) }
    }

    /**
     * Decrypt queryable encrypted data
     */
    fun decryptQueryable(encryptedText: String?): String? {
        return encryptedText?.let { queryableTextEncryptor.decrypt(it) }
    }

    /**
     * Encrypt financial amounts for secure storage
     */
    fun encryptAmount(amount: Double): String {
        return textEncryptor.encrypt(amount.toString())
    }

    /**
     * Decrypt financial amounts for calculations
     */
    fun decryptAmount(encryptedAmount: String): Double {
        return textEncryptor.decrypt(encryptedAmount).toDouble()
    }

    /**
     * Mask sensitive data for logging (show only last 4 characters)
     */
    fun maskSensitiveData(data: String?, visibleChars: Int = 4): String {
        return if (data == null || data.length <= visibleChars) {
            "*".repeat(data?.length ?: 0)
        } else {
            "*".repeat(data.length - visibleChars) + data.takeLast(visibleChars)
        }
    }

    /**
     * Validate if text is encrypted (basic check)
     */
    fun isEncrypted(text: String?): Boolean {
        if (text == null) return false
        return try {
            decrypt(text)
            true
        } catch (e: Exception) {
            false
        }
    }
}
