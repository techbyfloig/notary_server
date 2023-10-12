package com.techbyfloig.cubannotary.notary.model.ups.request.addressvalidation

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class XAVRequest(
    @SerializedName("AddressKeyFormat") val addressKeyFormat: AddressKeyFormat
): Serializable