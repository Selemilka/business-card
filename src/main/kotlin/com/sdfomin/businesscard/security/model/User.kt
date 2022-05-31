package com.sdfomin.businesscard.security.model

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


