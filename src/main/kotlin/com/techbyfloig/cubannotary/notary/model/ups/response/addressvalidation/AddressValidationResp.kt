package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressValidationResp(
    @SerializedName("XAVResponse") val xavResponse: XAVResponse
): Serializable