package com.sdfomin.businesscard.configuration.security

import com.sdfomin.businesscard.configuration.security.model.SecurityUserDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component

@Component
class SecurityUserDetailsService(
    private val userRepository: UserRepository,
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(userName: String): UserDetails {
        val user = userRepository.findByUsername(userName)
            ?: throw UsernameNotFoundException("User Not found: $userName")

        return SecurityUserDetails(user)
    }

}
