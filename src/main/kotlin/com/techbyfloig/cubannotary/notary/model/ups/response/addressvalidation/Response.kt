package com.techbyfloig.cubannotary.notary.model.ups.response.addressvalidation


import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Response(
    @SerializedName("Alert") val alert: Alert,
    @SerializedName("ResponseStatus") val responseStatus: ResponseStatus,
    @SerializedName("TransactionReference") val transactionReference: TransactionReference
): Serializable