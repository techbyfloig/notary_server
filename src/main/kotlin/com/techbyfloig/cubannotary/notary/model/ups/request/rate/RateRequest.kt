package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class RateRequest(
    @SerializedName("Request")
    val request: Request,
    @SerializedName("Shipment")
    val shipment: Shipment
)