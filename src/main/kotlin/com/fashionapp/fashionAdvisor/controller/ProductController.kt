package com.fashionapp.fashionAdvisor.controller

import com.fashionapp.fashionAdvisor.model.Product
import com.fashionapp.fashionAdvisor.repository.ProductRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val repository: ProductRepository) {

    @GetMapping
    fun getAllProducts(): List<Product> = repository.findAll()

    @PostMapping
    fun addProduct(@RequestBody product: Product): Product = repository.save(product)
}
