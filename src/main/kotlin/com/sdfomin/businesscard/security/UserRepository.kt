package com.sdfomin.businesscard.security

import com.sdfomin.businesscard.security.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String): User?
}

