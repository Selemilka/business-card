package com.sdfomin.businesscard.entity.applecard

abstract class DefaultAppleCard(
    val logo: String,
    val logoText: String,
    val headerFields: String,
    val thumbnail: String,
    val primaryField: String,
    val secondaryFields: String,
    val auxiliaryFields: String,
    val barcode: String,

    val fontColor: String,
    val backgroundColor: String,

)