package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class BudgetController {

    @GetMapping("/budget")
    fun getBudgetOverview(): ResponseEntity<Any> {
        // TODO: Implement budget overview logic
        val budgetData = mapOf(
            "totalBudget" to 0.0,
            "totalUsed" to 0.0,
            "totalRemaining" to 0.0,
            "usagePercentage" to 0.0,
            "categoryBudgets" to emptyList<Any>(),
            "subcategoryBudgets" to emptyList<Any>(),
            "monthlyComparison" to mapOf(
                "currentMonth" to 0.0,
                "previousMonth" to 0.0,
                "change" to 0.0
            ),
            "alerts" to emptyList<Any>(),
            "message" to "Budget overview data - implementation pending"
        )
        return ResponseEntity.ok(budgetData)
    }

    @PostMapping("/budget")
    fun createBudget(@RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement create budget logic
        val response = mapOf(
            "budget" to null,
            "message" to "Create budget - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PutMapping("/budget/{id}")
    fun updateBudget(@PathVariable id: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement update budget logic
        val response = mapOf(
            "budget" to null,
            "message" to "Update budget - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/budget/{id}")
    fun deleteBudget(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement delete budget logic
        val response = mapOf(
            "message" to "Delete budget - implementation pending"
        )
        return ResponseEntity.ok(response)
    }
}
