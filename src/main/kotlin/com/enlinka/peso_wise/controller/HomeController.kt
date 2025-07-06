package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class HomeController {

    @GetMapping("/home")
    fun getHomeData(): ResponseEntity<Any> {
        // TODO: Implement home dashboard data logic
        val homeData = mapOf(
            "totalBalance" to 0.0,
            "monthlyIncome" to 0.0,
            "monthlyExpenses" to 0.0,
            "budgetUsage" to 0.0,
            "recentTransactions" to emptyList<Any>(),
            "upcomingBills" to emptyList<Any>(),
            "savingsGoals" to emptyList<Any>(),
            "message" to "Home dashboard data - implementation pending"
        )
        return ResponseEntity.ok(homeData)
    }
}
