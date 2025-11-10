
package com.fashionapp.fashionAdvisor.controller

import com.fashionapp.fashionAdvisor.model.Product
import com.fashionapp.fashionAdvisor.service.RecommendationService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/recommendations")
class RecommendationController(
    private val recommendationService: RecommendationService
) {

    @GetMapping("/{userId}")
    fun getRecommendations(@PathVariable userId: Long): List<Product> {
        return recommendationService.getRecommendations(userId)
    }
}
