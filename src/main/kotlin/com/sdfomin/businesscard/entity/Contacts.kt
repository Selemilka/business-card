package com.sdfomin.businesscard.entity

data class Contacts(
    val id: Int = 0,
    val name: String,
    val website: String?,
    val email: String?,
    val phone: String?,
    val telegram: String?,
    val whatsapp: String?,
)
