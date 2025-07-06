package com.enlinka.peso_wise.util

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Component
import kotlin.random.Random

@Component
class EmailUtil {

    @Autowired
    private lateinit var mailSender: JavaMailSender

    /**
     * Generate a random 6-digit verification code
     */
    fun generateVerificationCode(): String {
        return Random.nextInt(100000, 999999).toString()
    }

    /**
     * Send verification email with code
     */
    fun sendVerificationEmail(toEmail: String, verificationCode: String) {
        val message = SimpleMailMessage().apply {
            from = "noreply@pesowise.com"
            setTo(toEmail)
            subject = "Peso Wise - Email Verification"
            text = buildVerificationEmailBody(verificationCode)
        }
        
        try {
            mailSender.send(message)
        } catch (e: Exception) {
            throw RuntimeException("Failed to send verification email to $toEmail", e)
        }
    }

    /**
     * Send password reset email
     */
    fun sendPasswordResetEmail(toEmail: String, resetCode: String) {
        val message = SimpleMailMessage().apply {
            from = "noreply@pesowise.com"
            setTo(toEmail)
            subject = "Peso Wise - Password Reset"
            text = buildPasswordResetEmailBody(resetCode)
        }
        
        try {
            mailSender.send(message)
        } catch (e: Exception) {
            throw RuntimeException("Failed to send password reset email to $toEmail", e)
        }
    }

    /**
     * Send welcome email to new users
     */
    fun sendWelcomeEmail(toEmail: String, userName: String) {
        val message = SimpleMailMessage().apply {
            from = "noreply@pesowise.com"
            setTo(toEmail)
            subject = "Welcome to Peso Wise!"
            text = buildWelcomeEmailBody(userName)
        }
        
        try {
            mailSender.send(message)
        } catch (e: Exception) {
            throw RuntimeException("Failed to send welcome email to $toEmail", e)
        }
    }

    /**
     * Build verification email body
     */
    private fun buildVerificationEmailBody(code: String): String {
        return """
            Welcome to Peso Wise!
            
            Please verify your email address by entering the following code in the app:
            
            Verification Code: $code
            
            This code will expire in 15 minutes.
            
            If you didn't create an account with Peso Wise, please ignore this email.
            
            Best regards,
            The Peso Wise Team
        """.trimIndent()
    }

    /**
     * Build password reset email body
     */
    private fun buildPasswordResetEmailBody(code: String): String {
        return """
            Password Reset Request
            
            You have requested to reset your password for your Peso Wise account.
            
            Reset Code: $code
            
            Enter this code in the app to reset your password. This code will expire in 15 minutes.
            
            If you didn't request a password reset, please ignore this email and your password will remain unchanged.
            
            Best regards,
            The Peso Wise Team
        """.trimIndent()
    }

    /**
     * Build welcome email body
     */
    private fun buildWelcomeEmailBody(userName: String): String {
        return """
            Hello $userName,
            
            Welcome to Peso Wise! Your account has been successfully created and verified.
            
            Peso Wise is your personal finance tracker that helps you:
            • Track your income and expenses
            • Set and achieve savings goals
            • Manage budgets across categories
            • Monitor your financial health
            
            Get started by logging into the app and exploring all the features we have to offer.
            
            If you have any questions or need support, feel free to contact us.
            
            Happy saving!
            The Peso Wise Team
        """.trimIndent()
    }

    /**
     * Validate email format
     */
    fun isValidEmail(email: String): Boolean {
        val emailRegex = "^[A-Za-z0-9+_.-]+@([A-Za-z0-9.-]+\\.[A-Za-z]{2,})$"
        return email.matches(emailRegex.toRegex())
    }

    /**
     * Mask email for logging (show first 2 chars and domain)
     */
    fun maskEmail(email: String): String {
        val parts = email.split("@")
        if (parts.size != 2) return "***@***.***"
        
        val localPart = parts[0]
        val domain = parts[1]
        
        val maskedLocal = if (localPart.length <= 2) {
            "*".repeat(localPart.length)
        } else {
            localPart.take(2) + "*".repeat(localPart.length - 2)
        }
        
        return "$maskedLocal@$domain"
    }
}
