package com.fashionapp.fashionAdvisor.controller

import com.fashionapp.fashionAdvisor.model.User
import com.fashionapp.fashionAdvisor.repository.UserRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userRepository: UserRepository) {

    @PostMapping
    fun addUser(@RequestBody user: User): User = userRepository.save(user)

    @GetMapping("/{id}")
    fun getUser(@PathVariable id: Long): User =
        userRepository.findById(id).orElseThrow { RuntimeException("User not found") }

    @PutMapping("/{id}")
    fun updateUser(@PathVariable id: Long, @RequestBody updated: User): User {
        val existing = userRepository.findById(id).orElseThrow { RuntimeException("User not found") }
        val user = existing.copy(
            name = updated.name,
            email = updated.email,
            stylePreference = updated.stylePreference,
            budget = updated.budget
        )
        return userRepository.save(user)
    }
}
