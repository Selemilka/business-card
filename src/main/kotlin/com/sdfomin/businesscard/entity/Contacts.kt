package com.sdfomin.businesscard.entity

data class Contacts(
    val id: Int = 0,
    val isActive: Boolean = false,
    val name: String? = null,
    val website: String? = null,
    val email: String? = null,
    val phone: String? = null,
    val telegram: String? = null,
)
