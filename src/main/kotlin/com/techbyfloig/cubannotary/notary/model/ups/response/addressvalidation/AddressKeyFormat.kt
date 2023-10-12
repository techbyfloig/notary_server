package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation

data class AddressKeyFormat(
    val AddressLine: String,
    val AttentionName: String,
    val ConsigneeName: String,
    val CountryCode: String,
    val PoliticalDivision1: String,
    val PoliticalDivision2: String,
    val PostcodeExtendedLow: String,
    val PostcodePrimaryLow: String,
    val Region: String,
    val Urbanization: String
) {

}