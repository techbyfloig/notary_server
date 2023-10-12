package com.techbyfloig.cubannotary.android.network.models.response.shipping

data class ItemizedCharge(
    val Code: String,
    val CurrencyCode: String,
    val MonetaryValue: String,
    val SubType: String
)