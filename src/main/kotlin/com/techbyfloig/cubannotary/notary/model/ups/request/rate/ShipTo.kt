package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class ShipTo(
    @SerializedName("Address")
    val address: Address,
    @SerializedName("Name")
    val name: String
)