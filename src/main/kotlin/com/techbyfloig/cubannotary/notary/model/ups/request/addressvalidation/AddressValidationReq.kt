package com.techbyfloig.cubannotary.notary.model.ups.request.addressvalidation

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AddressValidationReq(
    @SerializedName("XAVRequest") val xavRequest: XAVRequest
) : Serializable {

}
