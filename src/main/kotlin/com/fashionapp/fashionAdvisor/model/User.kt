package com.fashionapp.fashionAdvisor.model

import jakarta.persistence.*

@Entity
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val name: String,
    val email: String,
    val stylePreference: String,
    val budget: Int,
    val colorPreference: String = "",
    val favoriteBrand: String = "",
    val bodyType: String = "",
    val skinTone: String = ""
)


