package com.sdfomin.businesscard.db.dto

import javax.persistence.*

@Entity
@Table(name = "apple_card")
class AppleCardPg {

    companion object {
        const val DEFAULT_FONT_COLOR = "#000000"
        const val DEFAULT_BACKGROUND_COLOR = "#000000"
    }

    @Id
    @Column(name = "business_card_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Int = 0

    @Column(name = "font_color")
    var fontColor: String = DEFAULT_FONT_COLOR

    @Column(name = "background_color")
    var backgroundColor: String = DEFAULT_BACKGROUND_COLOR

    constructor()

    constructor(fontColor: String, backgroundColor: String) {
        this.fontColor = fontColor
        this.backgroundColor = backgroundColor
    }

}
