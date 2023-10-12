package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class TransactionReference(
    @SerializedName("CustomerContext")
    val customerContext: String,
    @SerializedName("TransactionIdentifier")
    val transactionIdentifier: String
)