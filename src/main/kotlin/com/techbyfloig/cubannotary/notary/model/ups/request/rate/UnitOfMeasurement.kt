package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class UnitOfMeasurement(
    @SerializedName("Code")
    val code: String,
    @SerializedName("Description")
    val description: String
)