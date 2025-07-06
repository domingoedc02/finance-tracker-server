package com.enlinka.peso_wise.controller

import com.enlinka.peso_wise.service.AuthService
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/auth")
class AuthController(
    private val authService: AuthService
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegisterRequest, httpRequest: HttpServletRequest): ResponseEntity<Any> {
        val result = authService.register(
            name = request.name,
            email = request.email,
            password = request.password,
            deviceId = request.deviceId
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/login")
    fun login(@RequestBody request: LoginRequest, httpRequest: HttpServletRequest): ResponseEntity<Any> {
        val result = authService.login(
            email = request.email,
            password = request.password,
            deviceId = request.deviceId
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/verification")
    fun verifyEmail(@RequestBody request: VerificationRequest, httpRequest: HttpServletRequest): ResponseEntity<Any> {
        val result = authService.verifyEmail(
            email = request.email,
            code = request.code,
            deviceId = request.deviceId
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/resend-verification")
    fun resendVerificationCode(@RequestBody request: ResendVerificationRequest, httpRequest: HttpServletRequest): ResponseEntity<Any> {
        val result = authService.resendVerificationCode(
            email = request.email,
            deviceId = request.deviceId
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/logout")
    fun logout(@RequestHeader("Authorization") token: String): ResponseEntity<Any> {
        val result = authService.logout(token)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/refresh-token")
    fun refreshToken(@RequestHeader("Authorization") token: String): ResponseEntity<Any> {
        val result = authService.refreshToken(token)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/change-password")
    fun changePassword(@RequestBody request: ChangePasswordRequest): ResponseEntity<Any> {
        val result = authService.changePassword(
            userId = request.userId,
            oldPassword = request.oldPassword,
            newPassword = request.newPassword
        )
        return ResponseEntity.ok(result)
    }

    @PostMapping("/reset-password")
    fun resetPassword(@RequestBody request: ResetPasswordRequest): ResponseEntity<Any> {
        val result = authService.resetPassword(request.email)
        return ResponseEntity.ok(result)
    }

    @PostMapping("/confirm-reset-password")
    fun confirmPasswordReset(@RequestBody request: ConfirmResetPasswordRequest): ResponseEntity<Any> {
        val result = authService.confirmPasswordReset(
            email = request.email,
            code = request.code,
            newPassword = request.newPassword
        )
        return ResponseEntity.ok(result)
    }

    @GetMapping("/me")
    fun getCurrentUser(@RequestHeader("Authorization") token: String): ResponseEntity<Any> {
        val result = authService.getCurrentUser(token)
        return ResponseEntity.ok(result)
    }
}

// Request DTOs
data class RegisterRequest(
    @field:NotBlank(message = "Name is required")
    @field:Size(max = 255, message = "Name must be less than 255 characters")
    val name: String,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:NotBlank(message = "Password is required")
    @field:Size(min = 8, max = 255, message = "Password must be between 8 and 255 characters")
    val password: String,

    val deviceId: String?
)

data class LoginRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:NotBlank(message = "Password is required")
    val password: String,

    val deviceId: String?
)

data class VerificationRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:NotBlank(message = "Code is required")
    @field:Size(max = 10, message = "Code must be less than 10 characters")
    val code: String,

    @field:NotBlank(message = "Device ID is required")
    val deviceId: String
)

data class ResendVerificationRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:NotBlank(message = "Device ID is required")
    val deviceId: String
)

data class ChangePasswordRequest(
    @field:NotBlank(message = "User ID is required")
    val userId: String,

    @field:NotBlank(message = "Old password is required")
    val oldPassword: String,

    @field:NotBlank(message = "New password is required")
    @field:Size(min = 8, max = 255, message = "New password must be between 8 and 255 characters")
    val newPassword: String
)

data class ResetPasswordRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String
)

data class ConfirmResetPasswordRequest(
    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:NotBlank(message = "Code is required")
    val code: String,

    @field:NotBlank(message = "New password is required")
    @field:Size(min = 8, max = 255, message = "New password must be between 8 and 255 characters")
    val newPassword: String
)
