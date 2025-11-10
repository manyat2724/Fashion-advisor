package com.fashionapp.fashionAdvisor.repository


import com.fashionapp.fashionAdvisor.model.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductRepository : JpaRepository<Product, Long>
