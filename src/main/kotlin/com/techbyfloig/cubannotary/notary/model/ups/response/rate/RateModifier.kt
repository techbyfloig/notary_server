package com.techbyfloig.cubannotary.notary.model.ups.response.rate

data class RateModifier(
    val Amount: String,
    val CurrencyCode: String,
    val ModifierDesc: String,
    val ModifierType: String
)