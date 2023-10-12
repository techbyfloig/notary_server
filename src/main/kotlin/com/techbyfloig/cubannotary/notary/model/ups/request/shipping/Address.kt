package com.techbyfloig.cubannotary.notary.model.ups.request.shipping

data class Address(
    val AddressLine: String,
    val City: String,
    val CountryCode: String,
    val PostalCode: String,
    val StateProvinceCode: String
)