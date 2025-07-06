package com.enlinka.peso_wise.controller

import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
@PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
class SubCategoryController {

    @GetMapping("/{category}/sub")
    fun getSubCategoriesByCategory(@PathVariable category: String,
                                  @RequestParam(defaultValue = "0") page: Int,
                                  @RequestParam(defaultValue = "20") size: Int): ResponseEntity<Any> {
        // TODO: Implement get subcategories by category logic
        val response = mapOf(
            "category" to category,
            "subcategories" to emptyList<Any>(),
            "page" to page,
            "size" to size,
            "totalElements" to 0,
            "totalPages" to 0,
            "message" to "Get subcategories for category '$category' - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @GetMapping("/{category}/sub/{id}")
    fun getSubCategoryById(@PathVariable category: String, @PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement get subcategory by id logic
        val response = mapOf(
            "category" to category,
            "subcategory" to null,
            "message" to "Get subcategory by ID for category '$category' - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PostMapping("/{category}/sub")
    fun createSubCategory(@PathVariable category: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement create subcategory logic
        val response = mapOf(
            "category" to category,
            "subcategory" to null,
            "message" to "Create subcategory for category '$category' - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @PutMapping("/{category}/sub/{id}")
    fun updateSubCategory(@PathVariable category: String, @PathVariable id: String, @RequestBody request: Any): ResponseEntity<Any> {
        // TODO: Implement update subcategory logic
        val response = mapOf(
            "category" to category,
            "subcategory" to null,
            "message" to "Update subcategory for category '$category' - implementation pending"
        )
        return ResponseEntity.ok(response)
    }

    @DeleteMapping("/{category}/sub/{id}")
    fun deleteSubCategory(@PathVariable category: String, @PathVariable id: String): ResponseEntity<Any> {
        // TODO: Implement delete subcategory logic
        val response = mapOf(
            "category" to category,
            "message" to "Delete subcategory for category '$category' - implementation pending"
        )
        return ResponseEntity.ok(response)
    }
}
