package com.techbyfloig.cubannotary.notary.model.ups.request.rate

import com.google.gson.annotations.SerializedName


data class Request(
    @SerializedName("TransactionReference")
    val transactionReference: TransactionReference
)