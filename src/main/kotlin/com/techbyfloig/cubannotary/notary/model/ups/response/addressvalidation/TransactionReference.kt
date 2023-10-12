package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation


import com.google.gson.annotations.SerializedName

data class TransactionReference(
    @SerializedName("CustomerContext") val customerContext: String
)