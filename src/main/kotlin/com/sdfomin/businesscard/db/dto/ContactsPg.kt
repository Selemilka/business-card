package com.sdfomin.businesscard.db.dto

import com.sdfomin.businesscard.entity.Contacts
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
@Table(name = "contact")
class ContactsPg {
    @Id
    @Column(name = "contacts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "is_active")
    val isActive: Boolean = false

    @Column(name = "name")
    var name: String? = null

    @Column(name = "website")
    var website: String? = null

    @Column(name = "email")
    var email: String? = null

    @Column(name = "phone")
    var phone: String? = null

    @Column(name = "telegram")
    var telegram: String? = null

    constructor()

    constructor(contacts: Contacts) {
        this.id = contacts.id
        this.name = contacts.name
        this.website = contacts.website
        this.email = contacts.email
        this.phone = contacts.phone
        this.telegram = contacts.telegram
    }
}

fun Contacts.convert() = ContactsPg(this)

interface ContactsRepo : JpaRepository<ContactsPg, Int>
