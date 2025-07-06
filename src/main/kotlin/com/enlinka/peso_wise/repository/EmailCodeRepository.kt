package com.enlinka.peso_wise.repository

import com.enlinka.peso_wise.model.EmailCode
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface EmailCodeRepository : JpaRepository<EmailCode, String> {
    
    fun findByEmailAndCodeAndIsUsedFalseAndIsExpiredFalse(email: String, code: String): Optional<EmailCode>
    
    fun findByEmail(email: String): List<EmailCode>
    
    fun findByDeviceId(deviceId: String): List<EmailCode>
    
    @Query("SELECT ec FROM EmailCode ec WHERE ec.email = :email AND ec.deviceId = :deviceId AND ec.isUsed = false AND ec.isExpired = false ORDER BY ec.createdAt DESC")
    fun findLatestValidCodeByEmailAndDevice(@Param("email") email: String, @Param("deviceId") deviceId: String): Optional<EmailCode>
    
    @Modifying
    @Query("UPDATE EmailCode ec SET ec.isExpired = true WHERE ec.createdAt < :expiredTime AND ec.isUsed = false")
    fun expireOldCodes(@Param("expiredTime") expiredTime: LocalDateTime): Int
    
    @Query("SELECT COUNT(ec) FROM EmailCode ec WHERE ec.email = :email AND ec.createdAt >= :since")
    fun countCodesSentToEmailSince(@Param("email") email: String, @Param("since") since: LocalDateTime): Long
    
    fun deleteByEmailAndIsUsedTrue(email: String): Int
}
