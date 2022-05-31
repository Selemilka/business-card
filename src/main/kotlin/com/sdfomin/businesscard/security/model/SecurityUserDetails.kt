package com.sdfomin.businesscard.security.model

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class SecurityUserDetails(
    private val userName: String,
    private val password: String,
    private val active: Boolean,
    private val authorities: List<GrantedAuthority>,
) : UserDetails {

    constructor(user: User) : this(
        userName = user.username,
        password = user.password,
        active = user.isActive,
        authorities = user.roles.split(",").map { SimpleGrantedAuthority(it) }.toList(),
    )

    override fun getAuthorities(): Collection<GrantedAuthority> = authorities

    override fun getPassword(): String = password

    override fun getUsername(): String = userName

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = active
}
