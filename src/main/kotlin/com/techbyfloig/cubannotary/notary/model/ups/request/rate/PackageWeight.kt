package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class PackageWeight(
    @SerializedName("UnitOfMeasurement")
    val unitOfMeasurement: UnitOfMeasurement,
    @SerializedName("Weight")
    val weight: String
)