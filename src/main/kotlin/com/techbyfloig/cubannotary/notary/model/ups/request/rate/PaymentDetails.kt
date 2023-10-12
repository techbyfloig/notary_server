package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class PaymentDetails(
    @SerializedName("ShipmentCharge")
    val shipmentCharge: ShipmentCharge
)