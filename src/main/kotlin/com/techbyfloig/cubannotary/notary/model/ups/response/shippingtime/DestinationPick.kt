package com.techbyfloig.cubannotary.android.network.models.response.shippingtime

data class DestinationPick(
    val city: String,
    val countryCode: String,
    val countryName: String,
    val postalCode: String,
    val postalCodeHigh: String,
    val postalCodeLow: String,
    val stateProvince: String,
    val town: String
)