package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class ItemizedCharge(
    val Code: String,
    val CurrencyCode: String,
    val Description: String,
    val MonetaryValue: String,
    val SubType: String
)