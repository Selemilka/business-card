package com.sdfomin.businesscard.configuration.security.model

import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
@Table(name = "users")
class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id = 0

    @Column(name = "username")
    var username: String = ""

    @Column(name = "password")
    var password: String = ""

    @Column(name = "active")
    var isActive = true

    @Column(name = "roles")
    var roles: String = "ROLE_USER"
}

interface UserRepo : JpaRepository<User, Int> {
    fun findByUsername(username: String): User
}
