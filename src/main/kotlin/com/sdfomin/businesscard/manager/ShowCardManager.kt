package com.sdfomin.businesscard.manager

import com.sdfomin.businesscard.db.service.BusinessCardService
import com.sdfomin.businesscard.entity.webcard.WebCard
import org.springframework.stereotype.Component

@Component
class ShowCardManager(
    private val businessCardService: BusinessCardService,
) {

    fun loadWebCard(handle: String): WebCard {
        val businessCard = businessCardService.load(handle)
        return WebCard(businessCard)
    }

    fun loadAllCards(userId: Int): List<WebCard> {
        val businessCards = businessCardService.loadAll(userId)
        return businessCards.map { WebCard(it) }
    }

}
