package com.techbyfloig.cubannotary.notary.model.ups.request.addressvalidation

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressKeyFormat(
    @SerializedName("AddressLine") val addressLine: List<String>, // line1, line2
    @SerializedName("BuildingName") val buildingName: String,
    @SerializedName("ConsigneeName") val consigneeName: String,
    @SerializedName("CountryCode") val countryCode: String = "US",
    @SerializedName("PoliticalDivision1") val politicalDivision1: String, // state
    @SerializedName("PoliticalDivision2") val politicalDivision2: String, // city
    @SerializedName("PostcodeExtendedLow") val postcodeExtendedLow: String,
    @SerializedName("PostcodePrimaryLow") val postcodePrimaryLow: String, // zip
    @SerializedName("Region") val region: String,
    @SerializedName("Urbanization") val urbanization: String
): Serializable