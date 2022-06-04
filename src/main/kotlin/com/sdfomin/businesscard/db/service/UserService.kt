package com.sdfomin.businesscard.db.service

import com.sdfomin.businesscard.configuration.security.model.User
import com.sdfomin.businesscard.configuration.security.model.UserRepo
import org.springframework.stereotype.Component

@Component
class UserService(
    private val userRepo: UserRepo
) {

    fun loadUser(username: String): User =
        userRepo.findByUsername(username)
}
