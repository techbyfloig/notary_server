package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation


import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class XAVResponse(
    @SerializedName("Response") val response: Response,
    @SerializedName("ValidAddressIndicator") val validAddressIndicator: String,
    @SerializedName("AmbiguousAddressIndicator") val ambiguousAddressIndicator: String,
    @SerializedName("NoCandidatesIndicator") val noCandidatesIndicator: String,
    @SerializedName("AddressClassification") val addressClassification: AddressClassification,
    @SerializedName("Candidate") val candidate: Candidate,
): Serializable