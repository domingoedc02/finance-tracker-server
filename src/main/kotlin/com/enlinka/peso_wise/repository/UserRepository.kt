package com.enlinka.peso_wise.repository

import com.enlinka.peso_wise.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, String> {
    
    fun findByEmail(email: String): Optional<User>
    
    fun findByDeviceId(deviceId: String): List<User>
    
    fun existsByEmail(email: String): Boolean
    
    @Query("SELECT u FROM User u WHERE u.email = :email AND u.deviceId = :deviceId")
    fun findByEmailAndDeviceId(@Param("email") email: String, @Param("deviceId") deviceId: String): Optional<User>
    
    @Query("SELECT u FROM User u WHERE u.name LIKE %:name%")
    fun findByNameContaining(@Param("name") name: String, pageable: Pageable): Page<User>
    
    @Query("SELECT COUNT(u) FROM User u WHERE u.createdAt >= CURRENT_DATE")
    fun countNewUsersToday(): Long
    
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.role WHERE u.email = :email")
    fun findByEmailWithRole(@Param("email") email: String): Optional<User>
}
