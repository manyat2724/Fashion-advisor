
package com.fashionapp.fashionAdvisor.service

import com.fashionapp.fashionAdvisor.model.Product
import com.fashionapp.fashionAdvisor.repository.ProductRepository
import com.fashionapp.fashionAdvisor.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class RecommendationService(
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {


            fun getRecommendations(userId: Long): List<Product> {
                val user = userRepository.findById(userId).orElseThrow {
                    IllegalArgumentException("User not found")
                }

                val allProducts = productRepository.findAll()

                return allProducts.filter { product ->
                    product.category.contains(user.stylePreference, ignoreCase = true)
                            && product.price <= user.budget
                            && (user.colorPreference.isBlank() || product.name.contains(user.colorPreference, ignoreCase = true))
                            && (user.favoriteBrand.isBlank() || product.brand.equals(user.favoriteBrand, ignoreCase = true))
                            && (user.bodyType.isBlank() || product.tags.contains(user.bodyType, ignoreCase = true))
                            && (user.skinTone.isBlank() || product.tags.contains(user.skinTone, ignoreCase = true))
                }

            }

        }


