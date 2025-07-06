package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/profile")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class ProfileController {

    @GetMapping
    fun getUserProfile(): ResponseEntity<Any> {
        // TODO: Implement get user profile logic
        val profileData = mapOf(
            "user" to mapOf(
                "id" to null,
                "name" to null,
                "email" to null,
                "deviceId" to null,
                "createdAt" to null,
                "updatedAt" to null
            ),
            "role" to null,
            "subscription" to mapOf(
                "status" to null,
                "type" to null,
                "startDate" to null,
                "expireDate" to null
            ),
            "statistics" to mapOf(
                "totalTransactions" to 0,
                "totalGoals" to 0,
                "totalCategories" to 0,
                "accountsLinked" to 0
            ),
            "message" to "Get user profile - implementation pending"
        )
        return ResponseEntity.ok(profileData)
    }

    @PutMapping
    fun updateUserProfile(@RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement update user profile logic
        val response = mapOf(
            "user" to null,
            "message" to "Update user profile - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/avatar")
    fun uploadAvatar(@RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement upload avatar logic
        val response = mapOf(
            "avatarUrl" to null,
            "message" to "Upload avatar - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/avatar")
    fun deleteAvatar(): ResponseEntity<Any> {
        // TODO: Implement delete avatar logic
        val response = mapOf(
            "message" to "Delete avatar - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/export")
    fun exportUserData(@RequestParam(defaultValue = "json") format: String): ResponseEntity<Any> {
        // TODO: Implement export user data logic
        val response = mapOf(
            "format" to format,
            "downloadUrl" to null,
            "message" to "Export user data in format '$format' - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping
    fun deleteUserAccount(): ResponseEntity<Any> {
        // TODO: Implement delete user account logic
        val response = mapOf(
            "message" to "Delete user account - implementation pending"
        )
        return ResponseEntity.ok(response)
    }
}
