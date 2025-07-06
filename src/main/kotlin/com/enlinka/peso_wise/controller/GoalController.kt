package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/goal")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class GoalController {

    @GetMapping
    fun getAllGoals(@RequestParam(defaultValue = "0") page: Int,
                   @RequestParam(defaultValue = "20") size: Int): ResponseEntity<Any> {
        // TODO: Implement get all goals logic
        val response = mapOf(
            "goals" to emptyList<Any>(),
            "page" to page,
            "size" to size,
            "totalElements" to 0,
            "totalPages" to 0,
            "message" to "Get all goals - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getGoalById(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement get goal by id logic
        val response = mapOf(
            "goal" to null,
            "message" to "Get goal by ID - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun createGoal(@RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement create goal logic
        val response = mapOf(
            "goal" to null,
            "message" to "Create goal - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updateGoal(@PathVariable id: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement update goal logic
        val response = mapOf(
            "goal" to null,
            "message" to "Update goal - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteGoal(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement delete goal logic
        val response = mapOf(
            "message" to "Delete goal - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{id}/contribute")
    fun contributeToGoal(@PathVariable id: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement contribute to goal logic
        val response = mapOf(
            "goal" to null,
            "contribution" to null,
            "message" to "Contribute to goal - implementation pending"
        )
        return ResponseEntity.ok(response)
    }
}
