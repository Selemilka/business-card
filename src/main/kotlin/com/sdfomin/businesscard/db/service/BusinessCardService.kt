package com.sdfomin.businesscard.db.service

import com.sdfomin.businesscard.db.dto.BusinessCardRepo
import com.sdfomin.businesscard.db.dto.ContactsRepo
import com.sdfomin.businesscard.db.dto.convert
import com.sdfomin.businesscard.db.dto.toBusinessCard
import com.sdfomin.businesscard.entity.BusinessCard
import org.springframework.stereotype.Component

@Component
class BusinessCardService(
    private val businessCardRepo: BusinessCardRepo,
    private val contactsRepo: ContactsRepo,
) {
    fun save(businessCard: BusinessCard) {
        businessCardRepo.save(businessCard.convert())
    }

    fun load(handle: String): BusinessCard =
        businessCardRepo.findByHandle(handle).toBusinessCard()

    fun loadAll(userId: Int): List<BusinessCard> =
        businessCardRepo.findAllByUserId(userId).map { it.toBusinessCard() }

}
