package com.sdfomin.businesscard.db.dto

import com.sdfomin.businesscard.entity.BusinessCard
import com.sdfomin.businesscard.entity.Contacts
import com.sdfomin.businesscard.entity.AppleCardAttributes
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.*

@Entity
@Table(name = "business_card")
class BusinessCardPg {

    @Id
    @Column(name = "business_card_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int = 0

    @Column(name = "user_id")
    var userId: Int = 0

    @Column(name = "handle")
    var handle: String = ""

    @Column(name = "logo_image")
    var logoImage: String? = null

    @Column(name = "description")
    var description: String = ""

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "business_contacts_id")
    var businessContactsPg: ContactsPg = ContactsPg()

    @OneToOne(fetch = FetchType.EAGER, cascade = [CascadeType.ALL], orphanRemoval = true)
    @JoinColumn(name = "person_contacts_id")
    var personContactsPg: ContactsPg = ContactsPg()

//    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.ALL], orphanRemoval = true)
//    @JoinColumn(name = "business_card_id")
//    var appleCardPg: AppleCardPg = AppleCardPg()

    constructor()

    constructor(businessCard: BusinessCard) {
        this.id = businessCard.id
        this.userId = businessCard.userId
        this.handle = businessCard.handle
        this.logoImage = businessCard.logoImage
        this.description = businessCard.description
        this.businessContactsPg = ContactsPg(businessCard.businessContacts)
        this.personContactsPg = ContactsPg(businessCard.personContacts)
//        this.appleCardPg = AppleCardPg()
    }
}

fun BusinessCard.convert() = BusinessCardPg(this)

fun BusinessCardPg.toBusinessCard() = BusinessCard(
    id = this.id,
    userId = this.userId,
    handle = this.handle,
    logoImage = this.logoImage,
    description = this.description,
    businessContacts = Contacts(
        id = this.businessContactsPg.id,
        isActive = this.businessContactsPg.isActive,
        name = this.businessContactsPg.name,
        website = this.businessContactsPg.website,
        email = this.businessContactsPg.email,
        phone = this.businessContactsPg.phone,
        telegram = this.businessContactsPg.telegram,
    ),
    personContacts = Contacts(
        id = this.personContactsPg.id,
        isActive = this.personContactsPg.isActive,
        name = this.personContactsPg.name,
        website = this.personContactsPg.website,
        email = this.personContactsPg.email,
        phone = this.personContactsPg.phone,
        telegram = this.personContactsPg.telegram,
    ),
//    appleCardAttributes = AppleCardAttributes(
//        id = this.appleCardPg.id,
//        backgroundColor = this.appleCardPg.backgroundColor,
//        fontColor = this.appleCardPg.fontColor,
//    ),
    appleCardAttributes = AppleCardAttributes()
)

interface BusinessCardRepo : JpaRepository<BusinessCardPg, Int> {
    fun findByHandle(handle: String): BusinessCardPg
    fun findAllByUserId(userId: Int): List<BusinessCardPg>
}
