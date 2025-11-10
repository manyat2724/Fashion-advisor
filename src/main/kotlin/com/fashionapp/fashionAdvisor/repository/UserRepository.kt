package com.fashionapp.fashionAdvisor.repository

import com.fashionapp.fashionAdvisor.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long>
