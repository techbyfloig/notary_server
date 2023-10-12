package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class Dimensions(
    @SerializedName("Height")
    val height: String,
    @SerializedName("Length")
    val length: String,
    @SerializedName("UnitOfMeasurement")
    val unitOfMeasurement: UnitOfMeasurement,
    @SerializedName("Width")
    val width: String
)