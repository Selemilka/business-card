package com.sdfomin.businesscard.db.dto

import com.sdfomin.businesscard.entity.BusinessCard
import com.sdfomin.businesscard.entity.Contacts
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToOne
import javax.persistence.Table

@Entity
@Table(name = "business_card")
class BusinessCardPg {

    @Id
    @Column(name = "business_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

//    @ManyToOne
//    @JoinColumn(name = "partner_id")
//    var partner: PartnerPg = PartnerPg()

    @Column(name = "handle")
    var handle: String = ""

    @Column(name = "logo_image")
    var logoImage: String? = null

    @Column(name = "description")
    var description: String? = null

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "business_contacts_id")
    var businessContacts: ContactsPg = ContactsPg()

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "person_contacts_id")
    var personContacts: ContactsPg = ContactsPg()

    constructor()

    constructor(businessCard: BusinessCard) {
        this.id = businessCard.id
        this.handle = businessCard.handle
        this.logoImage = businessCard.logoImage
        this.description = businessCard.description
        this.businessContacts = ContactsPg(businessCard.businessContacts)
        this.personContacts = ContactsPg(businessCard.personContacts)
    }
}

fun BusinessCard.convert() = BusinessCardPg(this)

interface BusinessCardRepo : JpaRepository<BusinessCardPg, Int>
