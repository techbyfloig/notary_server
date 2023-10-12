package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressClassification(
    @SerializedName("Code") val code: String,
    @SerializedName("Description") val description: String
): Serializable