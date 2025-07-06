package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/wallet")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class WalletController {

    @GetMapping
    fun getWalletOverview(): ResponseEntity<Any> {
        // TODO: Implement wallet overview logic
        val walletData = mapOf(
            "totalBalance" to 0.0,
            "accounts" to emptyList<Any>(),
            "cards" to emptyList<Any>(),
            "recentTransactions" to emptyList<Any>(),
            "monthlySpending" to 0.0,
            "availableCredit" to 0.0,
            "upcomingBills" to emptyList<Any>(),
            "message" to "Wallet overview data - implementation pending"
        )
        return ResponseEntity.ok(walletData)
    }

    @GetMapping("/card")
    fun getAllCards(@RequestParam(defaultValue = "0") page: Int,
                   @RequestParam(defaultValue = "20") size: Int): ResponseEntity<Any> {
        // TODO: Implement get all cards logic
        val response = mapOf(
            "cards" to emptyList<Any>(),
            "page" to page,
            "size" to size,
            "totalElements" to 0,
            "totalPages" to 0,
            "message" to "Get all cards - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/card/{id}")
    fun getCardById(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement get card by id logic
        val response = mapOf(
            "card" to null,
            "message" to "Get card by ID - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/card")
    fun createCard(@RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement create card logic
        val response = mapOf(
            "card" to null,
            "message" to "Create card - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PutMapping("/card/{id}")
    fun updateCard(@PathVariable id: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement update card logic
        val response = mapOf(
            "card" to null,
            "message" to "Update card - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/card/{id}")
    fun deleteCard(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement delete card logic
        val response = mapOf(
            "message" to "Delete card - implementation pending"
        )
        return ResponseEntity.ok(response)
    }
}
