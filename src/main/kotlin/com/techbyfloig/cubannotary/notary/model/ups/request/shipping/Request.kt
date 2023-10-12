package com.techbyfloig.cubannotary.notary.model.ups.request.shipping



data class Request(
    val RequestOption: String,
    val SubVersion: String,
    val TransactionReference: TransactionReference
)