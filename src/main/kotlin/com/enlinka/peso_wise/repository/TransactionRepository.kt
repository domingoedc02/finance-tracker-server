package com.enlinka.peso_wise.repository

import com.enlinka.peso_wise.model.Transaction
import com.enlinka.peso_wise.model.User
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import java.time.LocalDateTime
import java.util.*

@Repository
interface TransactionRepository : JpaRepository<Transaction, String> {
    
    fun findByUser(user: User, pageable: Pageable): Page<Transaction>
    
    fun findByUserAndCategory(user: User, category: String, pageable: Pageable): Page<Transaction>
    
    fun findByUserAndSubcategory(user: User, subcategory: String, pageable: Pageable): Page<Transaction>
    
    fun findByUserAndStatus(user: User, status: String, pageable: Pageable): Page<Transaction>
    
    fun findByGroupId(groupId: String): List<Transaction>
    
    @Query("SELECT t FROM Transaction t WHERE t.user = :user AND t.createdAt BETWEEN :startDate AND :endDate ORDER BY t.createdAt DESC")
    fun findByUserAndDateRange(@Param("user") user: User, @Param("startDate") startDate: LocalDateTime, @Param("endDate") endDate: LocalDateTime, pageable: Pageable): Page<Transaction>
    
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.createdAt >= :since")
    fun getTotalAmountByUserSince(@Param("user") user: User, @Param("since") since: LocalDateTime): Double?
    
    @Query("SELECT t.category, SUM(t.amount) FROM Transaction t WHERE t.user = :user AND t.createdAt >= :since GROUP BY t.category ORDER BY SUM(t.amount) DESC")
    fun getExpensesByCategory(@Param("user") user: User, @Param("since") since: LocalDateTime): List<Array<Any>>
    
    @Query("SELECT COUNT(t) FROM Transaction t WHERE t.user = :user AND t.createdAt >= CURRENT_DATE")
    fun countTodayTransactions(@Param("user") user: User): Long
    
    @Query("SELECT t FROM Transaction t WHERE t.user = :user ORDER BY t.createdAt DESC")
    fun findRecentTransactionsByUser(@Param("user") user: User, pageable: Pageable): Page<Transaction>
}
