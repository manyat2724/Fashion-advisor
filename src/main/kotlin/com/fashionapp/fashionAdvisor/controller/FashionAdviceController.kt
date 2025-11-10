package com.fashionapp.fashionAdvisor.controller

import org.springframework.web.bind.annotation.*
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.*
import org.springframework.web.client.RestTemplate

@RestController
@RequestMapping("/api/ai")
class FashionAdviceController(
    @Value("\${huggingface.api.key}") private val apiKey: String
) {

    @PostMapping("/advice")
    fun getFashionAdvice(@RequestBody request: Map<String, String>): Map<String, String> {
        val userPrompt = request["prompt"] ?: "Give a simple fashion tip."

        // ✅ Latest Hugging Face endpoint (as of Nov 2025)
        val url = "https://api-inference.huggingface.co/v1/chat/completions"

        val headers = HttpHeaders().apply {
            set("Authorization", "Bearer $apiKey")
            contentType = MediaType.APPLICATION_JSON
        }

        val body = mapOf(
            "model" to "HuggingFaceH4/zephyr-7b-beta", // ✅ working free-tier model
            "messages" to listOf(
                mapOf("role" to "system", "content" to "You are an AI fashion stylist."),
                mapOf("role" to "user", "content" to userPrompt)
            ),
            "max_tokens" to 200
        )

        val entity = HttpEntity(body, headers)
        val restTemplate = RestTemplate()

        return try {
            val response = restTemplate.postForEntity(url, entity, Map::class.java)
            val result = (((response.body?.get("choices") as? List<*>)?.firstOrNull() as? Map<*, *>)?.get("message") as? Map<*, *>)?.get("content") ?: "No advice generated."
            mapOf("advice" to result.toString())
        } catch (e: Exception) {
            mapOf("error" to "Hugging Face API request failed: ${e.message}")
        }
    }
}


