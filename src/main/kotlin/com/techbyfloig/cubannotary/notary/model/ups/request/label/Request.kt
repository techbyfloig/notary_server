package com.techbyfloig.cubannotary.android.network.models.request.label

data class Request(
    val RequestOption: String,
    val SubVersion: String,
    val TransactionReference: TransactionReference
)