package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation


import com.google.gson.annotations.SerializedName

data class ResponseStatus(
    @SerializedName("Code") val code: String,
    @SerializedName("Description") val description: String
)