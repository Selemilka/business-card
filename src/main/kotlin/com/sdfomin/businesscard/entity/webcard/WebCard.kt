package com.sdfomin.businesscard.entity.webcard

import com.sdfomin.businesscard.entity.BusinessCard
import com.sdfomin.businesscard.entity.Contacts

class WebCard(
    val handle: String,
    val logoImage: String?,
    val businessName: String?,
    val personName: String?,
    val description: String?,

    val business: WebContacts,
    val person: WebContacts,
) {

    constructor(businessCard: BusinessCard) : this(
        handle = businessCard.handle,
        logoImage = businessCard.logoImage,

        businessName = if (businessCard.businessContacts.isActive)
            businessCard.businessContacts.name else null,
        personName = if (businessCard.personContacts.isActive)
            businessCard.personContacts.name else null,
        description = businessCard.description,

        business = WebContacts(businessCard.businessContacts),
        person = WebContacts(businessCard.personContacts),
    )
}

class WebContacts(
    val isActive: Boolean,
    val website: String?,
    val email: String?,
    val phone: String?,
    val telegram: String?,
) {
    constructor(contacts: Contacts) : this(
        isActive = contacts.isActive,
        website = contacts.website,
        email = contacts.email,
        phone = contacts.phone,
        telegram = contacts.telegram,
    )
}
