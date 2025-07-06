package com.enlinka.peso_wise.security

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@Component
class LoginAttemptLogger {
    
    private val logger = LoggerFactory.getLogger("LOGIN_ATTEMPTS")
    
    fun logSuccessfulLogin(email: String, deviceId: String?, ipAddress: String) {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val message = "SUCCESSFUL_LOGIN - Email: $email, Device: ${deviceId ?: "unknown"}, IP: $ipAddress, Time: $timestamp"
        logger.info(message)
    }
    
    fun logFailedLogin(email: String, deviceId: String?, ipAddress: String, reason: String) {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val message = "FAILED_LOGIN - Email: $email, Device: ${deviceId ?: "unknown"}, IP: $ipAddress, Reason: $reason, Time: $timestamp"
        logger.warn(message)
    }
    
    fun logRegistrationAttempt(email: String, deviceId: String?, ipAddress: String, success: Boolean) {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val status = if (success) "SUCCESSFUL" else "FAILED"
        val message = "${status}_REGISTRATION - Email: $email, Device: ${deviceId ?: "unknown"}, IP: $ipAddress, Time: $timestamp"
        if (success) {
            logger.info(message)
        } else {
            logger.warn(message)
        }
    }
    
    fun logEmailVerification(email: String, deviceId: String?, ipAddress: String, success: Boolean) {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val status = if (success) "SUCCESSFUL" else "FAILED"
        val message = "${status}_EMAIL_VERIFICATION - Email: $email, Device: ${deviceId ?: "unknown"}, IP: $ipAddress, Time: $timestamp"
        if (success) {
            logger.info(message)
        } else {
            logger.warn(message)
        }
    }
    
    fun logSuspiciousActivity(email: String?, deviceId: String?, ipAddress: String, activity: String) {
        val timestamp = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
        val message = "SUSPICIOUS_ACTIVITY - Email: ${email ?: "unknown"}, Device: ${deviceId ?: "unknown"}, IP: $ipAddress, Activity: $activity, Time: $timestamp"
        logger.error(message)
    }
}
