package com.fashionapp.fashionAdvisor.model



import jakarta.persistence.*

@Entity
data class Product(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String="",
    val category: String="",
    val price: Int=0,
    val brand: String="",
    val tags: String = ""
)
