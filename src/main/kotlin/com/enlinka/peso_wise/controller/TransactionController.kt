package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/transaction")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class TransactionController {

    @GetMapping
    fun getAllTransactions(@RequestParam(defaultValue = "0") page: Int,
                          @RequestParam(defaultValue = "20") size: Int): ResponseEntity<Any> {
        // TODO: Implement get all transactions logic
        val response = mapOf(
            "transactions" to emptyList<Any>(),
            "page" to page,
            "size" to size,
            "totalElements" to 0,
            "totalPages" to 0,
            "message" to "Get all transactions - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getTransactionById(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement get transaction by id logic
        val response = mapOf(
            "transaction" to null,
            "message" to "Get transaction by ID - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun createTransaction(@RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement create transaction logic
        val response = mapOf(
            "transaction" to null,
            "message" to "Create transaction - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updateTransaction(@PathVariable id: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement update transaction logic
        val response = mapOf(
            "transaction" to null,
            "message" to "Update transaction - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement delete transaction logic
        val response = mapOf(
            "message" to "Delete transaction - implementation pending"
        )
        return ResponseEntity.ok(response)
    }
}
