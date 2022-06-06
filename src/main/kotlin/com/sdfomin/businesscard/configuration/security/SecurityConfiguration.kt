package com.sdfomin.businesscard.configuration.security

import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfiguration(
    var userDetailsService: UserDetailsService,
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService)
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
            .antMatchers("/manage/admin").hasRole("ADMIN")
//            .antMatchers("/manage/**").hasAnyRole("ADMIN", "USER")
            .antMatchers("/").hasAnyRole("ADMIN", "USER")
            .antMatchers("/*").permitAll()
            .and().csrf().disable()
            .formLogin()
            .loginPage("/login")
    }

    @get:Bean
    val passwordEncoder: PasswordEncoder
        get() = NoOpPasswordEncoder.getInstance()
}
