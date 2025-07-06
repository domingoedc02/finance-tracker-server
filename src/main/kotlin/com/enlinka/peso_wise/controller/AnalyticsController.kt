package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class AnalyticsController {

    @GetMapping("/analytics")
    fun getAnalyticsData(@RequestParam(defaultValue = "month") period: String): ResponseEntity<Any> {
        // TODO: Implement analytics data logic
        val analyticsData = mapOf(
            "period" to period,
            "totalIncome" to 0.0,
            "totalExpenses" to 0.0,
            "netSavings" to 0.0,
            "categoryBreakdown" to emptyList<Any>(),
            "monthlyTrends" to emptyList<Any>(),
            "topExpenseCategories" to emptyList<Any>(),
            "budgetPerformance" to mapOf(
                "totalBudget" to 0.0,
                "used" to 0.0,
                "remaining" to 0.0,
                "percentage" to 0.0
            ),
            "message" to "Analytics data for period '$period' - implementation pending"
        )
        return ResponseEntity.ok(analyticsData)
    }
}
