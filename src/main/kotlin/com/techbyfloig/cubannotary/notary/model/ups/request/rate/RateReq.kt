package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class RateReq(
    @SerializedName("RateRequest")
    val rateRequest: RateRequest?
)