package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/category")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class CategoryController {

    @GetMapping
    fun getAllCategories(@RequestParam(defaultValue = "0") page: Int,
                        @RequestParam(defaultValue = "20") size: Int): ResponseEntity<Any> {
        // TODO: Implement get all categories logic
        val response = mapOf(
            "categories" to emptyList<Any>(),
            "page" to page,
            "size" to size,
            "totalElements" to 0,
            "totalPages" to 0,
            "message" to "Get all categories - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{id}")
    fun getCategoryById(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement get category by id logic
        val response = mapOf(
            "category" to null,
            "message" to "Get category by ID - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping
    fun createCategory(@RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement create category logic
        val response = mapOf(
            "category" to null,
            "message" to "Create category - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{id}")
    fun updateCategory(@PathVariable id: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement update category logic
        val response = mapOf(
            "category" to null,
            "message" to "Update category - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{id}")
    fun deleteCategory(@PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement delete category logic
        val response = mapOf(
            "message" to "Delete category - implementation pending"
        )
        return ResponseEntity.ok(response)
    }
}
