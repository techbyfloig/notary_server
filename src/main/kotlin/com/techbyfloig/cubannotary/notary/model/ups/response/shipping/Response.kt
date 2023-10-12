package com.techbyfloig.cubannotary.android.network.models.response.shipping

data class Response(
    val Alert: Alert,
    val ResponseStatus: ResponseStatus,
    val TransactionReference: TransactionReference
)