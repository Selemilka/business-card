package com.sdfomin.businesscard.dto

import com.sdfomin.businesscard.entity.Contacts
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "contact")
class ContactsPg {
    @Id
    @Column(name = "contacts_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "name")
    var name: String = ""

    @Column(name = "website")
    var website: String? = null

    @Column(name = "email")
    var email: String? = null

    @Column(name = "phone")
    var phone: String? = null

    @Column(name = "telegram")
    var telegram: String? = null

    @Column(name = "whatsapp")
    var whatsapp: String? = null

    constructor()

    constructor(contacts: Contacts) {
        this.id = contacts.id
        this.name = contacts.name
        this.website = contacts.website
        this.email = contacts.email
        this.phone = contacts.phone
        this.telegram = contacts.telegram
        this.whatsapp = contacts.whatsapp
    }
}

interface ContactsRepo : JpaRepository<ContactsPg, Int> {
}
