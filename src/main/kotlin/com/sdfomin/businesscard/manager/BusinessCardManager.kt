package com.sdfomin.businesscard.manager

import com.sdfomin.businesscard.db.service.BusinessCardService
import com.sdfomin.businesscard.entity.BusinessCard
import com.sdfomin.businesscard.entity.webcard.WebCard
import org.springframework.core.io.Resource
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Component

@Component
class BusinessCardManager(
    private val businessCardService: BusinessCardService,
    private val appleCardManager: AppleCardManager,
) {

    fun loadWebCard(handle: String): WebCard {
        val businessCard = businessCardService.load(handle)
        return WebCard(businessCard)
    }

    fun loadAllCards(userId: Int): List<WebCard> {
        val businessCards = businessCardService.loadAll(userId)
        return businessCards.map { WebCard(it) }
    }

    fun getAppleCard(handle: String): ResponseEntity<Resource> {
        return appleCardManager.generate(businessCardService.load(handle))
    }

    fun createCard(businessCard: BusinessCard) {
        businessCardService.save(businessCard)
    }

}
