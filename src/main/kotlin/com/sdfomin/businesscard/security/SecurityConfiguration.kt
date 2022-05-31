package com.sdfomin.businesscard.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import kotlin.Throws
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import java.lang.Exception

@EnableWebSecurity
class SecurityConfiguration internal constructor(var userDetailsService: UserDetailsService) :
    WebSecurityConfigurerAdapter() {
    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/manage/admin").hasRole("ADMIN")
            .antMatchers("/manage/user").hasAnyRole("ADMIN", "USER")
            .antMatchers("/").permitAll()
            .and().csrf().disable()
            .formLogin()
    }

    @get:Bean
    val passwordEncoder: PasswordEncoder
        get() = NoOpPasswordEncoder.getInstance()
}
