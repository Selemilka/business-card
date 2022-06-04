package com.sdfomin.businesscard.entity.applecard

import com.sdfomin.businesscard.entity.BusinessCard

abstract class AppleCard(val businessCard: BusinessCard) {

    fun getLogo(): String? = businessCard.logoImage

    fun getLogoText(): String = ""

    fun getHeaderFields(): String = ""

    fun getThumbnail(): String = ""

    abstract fun getPrimaryField(): String

    abstract fun getSecondaryFields(): String

    abstract fun getAuxiliaryFields(): String

    fun getBarcode(): String = ""

    fun getFontColor(): String = businessCard.appleCardAttributes.fontColor

    fun getBackgroundColor(): String = businessCard.appleCardAttributes.backgroundColor

    companion object {

        fun convert(businessCard: BusinessCard): AppleCard {
            val personActive = businessCard.personContacts.isActive
            val businessActive = businessCard.businessContacts.isActive

            return if (personActive && businessActive) {
                MultiAppleCard(businessCard)
            } else if (personActive) {
                PersonAppleCard(businessCard)
            } else if (businessActive) {
                BusinessAppleCard(businessCard)
            } else {
                throw Exception("Both types disabled")
            }
        }

    }

}
