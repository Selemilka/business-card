package com.sdfomin.businesscard.configuration.security

import com.sdfomin.businesscard.configuration.security.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Int> {
    fun findByUsername(username: String): User?
}
